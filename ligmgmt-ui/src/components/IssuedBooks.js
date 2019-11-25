import React, {Component} from 'react';
import ReactTable from "react-table";
import { createBrowserHistory } from 'history';
const history = createBrowserHistory();

class IssuedBooks extends Component{
    constructor(props) {
        super(props);
        this.state = {books: []};

    }

    componentDidMount() {
        let userInfo = sessionStorage.getItem('userInfo');
        if(userInfo && userInfo.length) {
            userInfo = JSON.parse(userInfo);
            document.querySelector('.welcome-note').innerHTML = 'Welcome ' + userInfo.sub;
            document.querySelector('.Header ul').style.display = 'block';
        } else {
            window.location = window.location.protocol + '//' + window.location.host;
        }
        this.fetchBooks(1);

    }

    render() {
        const columns = [{
            Header: 'Borrower',
            accessor: 'userName'
        },{
            Header: 'Book',
            accessor: 'bookName'
        },  {
            Header: 'Author',
            accessor: 'authorName'
        }, {
            Header: 'Category',
            accessor: 'category'
        },{
            Header: 'Date of Issue',
            accessor: 'dateOfIssue'
        },{
            Header: 'Date of Return',
            accessor: 'dateOfReturn'
        }];
        return (
            <div>
                <h1>Issued Books</h1>
                <ReactTable
                    data={this.state.books}
                    columns={columns}
                />

            </div>

        )
    }


    fetchBooks = () => {

        const jwtToken = sessionStorage.getItem("jwt");
        const userInfo = JSON.parse(sessionStorage.getItem('userInfo'));
        var url = 'http://localhost:8080/v1/borrower';
        if(userInfo.roles === 'ROLE_STUDENT') {
            url += '/user/' + userInfo.sub;
        }
        fetch(url,
            {headers: {"Authorization": jwtToken, "Content-Type": "application/json"}}
        )
            .then((response) => response.json())
            .then((responseData) => {
                if(responseData.status === 401) {
                    sessionStorage.removeItem('jwt');
                    history.push('/');
                    window.location.reload();
                } else {
                    this.setState({
                        books: responseData.result
                    });
                }

            })
            .catch(err => {

            });
    }
}

export default IssuedBooks;