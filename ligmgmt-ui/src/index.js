import React from 'react';
import ReactDOM from 'react-dom';
import {Route, BrowserRouter as Router, Link, Switch} from 'react-router-dom'
import './index.css';
import App from './App';
import IssuedBooks from "./components/IssuedBooks";
import * as serviceWorker from './serviceWorker';
import BookMgmt from "./components/BookMgmt";


const routing = (
    <Router>
        <div className="App">
            <header className="Header">
                <div className="container">
                    <h1 className="App-title" align="left">Library</h1>
                    <h3 className="welcome-note pull-right"></h3>
                    <ul>
                        <li>
                            <Link to="/books">Books</Link>
                        </li>
                        <li>
                            <Link to="/issuedbooks">Issued Books</Link>
                        </li>
                        <li>
                            <a href="" onClick={() =>{
                                sessionStorage.removeItem('jwt');
                                sessionStorage.removeItem('userInfo');
                                window.location = window.location.protocol + '//' + window.location.host;
                            }}>Logout</a>
                        </li>
                    </ul>

                </div>

            </header>
            <div className="container">
                <Switch>
                    <Route exact path="/" component={App} />
                    <Route path="/books" component={BookMgmt} />
                    <Route path="/issuedbooks" component={IssuedBooks} />
                </Switch>
            </div>
        </div>
    </Router>
);
ReactDOM.render(routing, document.getElementById('root'));

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
