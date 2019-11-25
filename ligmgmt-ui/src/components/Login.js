import 'bootstrap/dist/css/bootstrap.min.css';
import React, { Component } from 'react';
import Util from "./util";
import { createBrowserHistory } from 'history';
const history = createBrowserHistory();

class Login extends Component {
    index = 20;
    constructor(props) {
        super(props);

        this.state = {
            username: '',
            password: '',
            isAuthenticated: (sessionStorage.getItem("jwt")?true:false),
            open: false, invalid: false
        };
    }

    login = () => {
        const user = {userName: this.state.username, password: this.state.password};
        fetch("http://localhost:8080/v1/login", {
            headers: {"Content-Type": "application/json"},
            method: 'POST',
            body: JSON.stringify(user)
        })
            .then(res => res.json())
            .then(res => {
                if (res.status == "200") {
                    console.log("res status :"+res.status);
                console.log("message :" + res.message);
                const jwtToken = res.message;
                if (jwtToken !== null) {
                    sessionStorage.setItem("jwt", jwtToken);
                    let util = new Util();
                    let userInfo = util.parseJwt(jwtToken);
                    document.querySelector('.welcome-note').innerHTML = 'Welcome ' + userInfo.sub;
                    sessionStorage.setItem('userInfo', JSON.stringify(userInfo));
                    this.setState({isAuthenticated: true, invalid: false});
                } else {
                    this.setState({open: true, invalid: false});
                }
            } else {
                    this.setState({
                        invalid: true
                    })
                }
            })
            .catch(err => {
                this.setState({
                    invalid: true
                });
            })
    };

    handleChange = (event) => {
        this.setState({[event.target.name] : event.target.value});
    };

    render() {
        if (this.state.isAuthenticated === true) {
            history.push('/books');
            window.location.reload();
            return '';
        }
        else {
            return (
                <div id="login">
                    <h3 className="text-center text-white pt-5">Login form</h3>
                    <div className="container">
                        <div id="login-row" className="row justify-content-center align-items-center">
                        <div id="login-column" className="col-md-6">
                         <div id="login-box" className="col-md-12">
                        <div className="form-group">
                            <input type="text" name="username" onChange={this.handleChange} className="form-control" placeholder="username" />
                        </div>
                        <div className="form-group">
                            <input type="password" name="password" onChange={this.handleChange}  className="form-control" placeholder="password" />
                        </div>
                             <input type="submit" name="submit" onClick={this.login} className="btn btn-info btn-md" value="Login"/>
                         </div>
                            <div className={'error-info ' + (this.state.invalid ? 'active': '')}>Invalid user name or password!</div>
                        </div>
                        </div>
                </div>
                </div>

            );
        }
    }
}

export default Login;