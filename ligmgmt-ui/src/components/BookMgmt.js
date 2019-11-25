import React, {Component} from 'react';
import ReactTable from "react-table";
import 'react-table/react-table.css';
import AddBook from './AddBook';
import Pagination from "react-js-pagination";
import { createBrowserHistory } from 'history';
const history = createBrowserHistory();

class BookMgmt extends Component {
    constructor(props) {
        super(props);
        this.state = {books: [], activePage: 1, itemPerPage: 4, totalItems: 0, search: '', role: ''};
    }

    componentDidMount() {
        let userInfo = sessionStorage.getItem('userInfo');
        if(userInfo && userInfo.length) {
            userInfo = JSON.parse(userInfo);
            this.setState({role: userInfo.roles});
            document.querySelector('.welcome-note').innerHTML = 'Welcome ' + userInfo.sub;
            document.querySelector('.Header ul').style.display = 'block';
        } else {
            window.location = window.location.protocol + '//' + window.location.host;
        }
        this.fetchBooks(1);

    }


    addBook(book) {
        const jwtToken = sessionStorage.getItem("jwt");
        if(!jwtToken) {
            window.location = window.location.protocol + '//' + window.location.host;
        }
        fetch('http://localhost:8080/v1/book/', {
            method: 'POST',
            headers: {
                "Authorization": jwtToken,
                "Content-Type": "application/json"
            },
            body: JSON.stringify(book)
        })
            .then(res => {
                console.log(res);
                let className = "";

                if(res.status === 201) {
                    className = '.add-success-info';
                } else {
                    className = '.add-error-info';
                }
                document.querySelector(className).style.display = 'block';

                setTimeout(()=> document.querySelector(className).style.display = 'none', 3000);

                this.fetchBooks();

            }).catch(err => console.log(err))
    }


    editable = (cell) => {
        return (
            <div style={{backgroundColor: "#fafafa"}} contentEditable suppressContentEditableWarning onBlur={e => {
                const curr = [...this.state.books];
                curr[cell.index][cell.column.id] = e.target.innerHTML;
                this.setState({books: curr});
            }}
                 dangerouslySetInnerHTML={{__html: this.state.books[cell.index][cell.column.id]}}
            />
        );
    };
    render() {

        const columns = [{
            Header: 'Book',
            accessor: 'bookName'
        }, {
            Header: 'Book Desc',
            accessor: 'bookDesc'
        }, {
            Header: 'Author',
            accessor: 'bookAuthor'
        }, {
            Header: 'Category',
            accessor: 'bookCategory'
        },{
            Header: 'Availability',
            accessor: 'bookAvailability'
        }

            ,];

        return (
            <div>
                <div className={(this.state.role === 'ROLE_STUDENT') ? 'hide': 'show'}>
                 <AddBook addBook={this.addBook} fetchBooks={this.fetchBooks}/>
                </div>
                <div className="add-success-info">Successfully added!</div>
                <div className="add-error-info">Unable to save!</div>
                <div className="search-book">
                    <input className="form-control" value={this.state.value} placeholder="Search" onChange={(e)=>{
                        this.setState({
                            activePage: 1,
                            search: e.target.value
                        });

                        this.fetchBooks(1, e.target.value);
                    }}/>
                </div>

                <ReactTable
                    data={this.state.books}
                    columns={columns}
                    pageSize={this.state.itemPerPage}
                />

                <div>
                    <Pagination
                        activePage={this.state.activePage}
                        itemsCountPerPage={this.state.itemPerPage}
                        totalItemsCount={this.state.totalItems}
                        pageRangeDisplayed={5}
                        onChange={(index) => {
                            this.setState({
                                activePage: index
                            });

                            this.fetchBooks(index);
                            }
                        }
                    />
                </div>


            </div>
        );
    }


    fetchBooks = (index, search) => {
        index = (index === undefined) ? this.state.activePage : index;
        search = (search === undefined) ? this.state.search : search;
        const jwtToken = sessionStorage.getItem("jwt");
        if(!jwtToken) {
            window.location = window.location.protocol + '//' + window.location.host;
        }
        fetch('http://localhost:8080/v1/book/searchwithpage?name='+ search +'&page='+ (index -1) +'&size=' + this.state.itemPerPage,
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
                        books: responseData.content,
                        totalItems: responseData.totalElements
                    });
                }

            })
            .catch(err => {

            });
    }

}

export default BookMgmt;
