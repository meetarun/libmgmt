import React, {Component} from 'react';
import SkyLight from 'react-skylight';

class AddBook extends Component {

    constructor(props) {
        super(props);
        this.state = {bookName: '', bookDesc: '', bookIsbn: '', bookAuthor: '', bookPublication: '', bookCategory: '', bookMediaType: '', bookPrice: '',bookEdition: '', bookInductionDate: '', bookAvailability: ''};
    }

    handleChange = (event) => {
        this.setState(
            {[event.target.name]: event.target.value}
        );
    };

    handleSubmit = (event) => {
        event.preventDefault();
        var book = {bookName: this.state.bookName, bookDesc: this.state.bookDesc, bookIsbn: this.state.bookIsbn, bookAuthor: this.state.bookAuthor,
            bookPublication: this.state.bookPublication, bookCategory: this.state.bookCategory, bookMediaType: this.state.bookMediaType, bookPrice: this.state.bookPrice,
            bookEdition: this.state.bookEdition, bookInductionDate: this.state.bookInductionDate, bookAvailability: this.state.bookAvailability};
        this.props.addBook(book);
        this.refs.addDialog.hide();
    }

    render() {
        return (
            <div>
                <SkyLight hideOnOverlayClicked ref="addDialog">
                    <h3>Add Book</h3>
                    <form>
                        <input className="form-control" type="text" placeholder="Name" name="bookName" onChange={this.handleChange}/><br/>
                        <input className="form-control" type="text" placeholder="Description" name="bookDesc" onChange={this.handleChange}/><br/>
                        <input className="form-control" type="text" placeholder="Author" name="bookAuthor" onChange={this.handleChange}/><br/>
                        <input className="form-control" type="text" placeholder="ISBN" name="bookIsbn" onChange={this.handleChange}/><br/>
                        <input className="form-control" type="text" placeholder="Publication" name="bookPublication" onChange={this.handleChange}/><br/>
                        <input className="form-control" type="text" placeholder="Category" name="bookCategory" onChange={this.handleChange}/><br/>
                        <input className="form-control" type="text" placeholder="Type" name="bookMediaType" onChange={this.handleChange}/><br/>
                        <input className="form-control" type="text" placeholder="Price" name="bookPrice" onChange={this.handleChange}/><br/>
                        <input className="form-control" type="text" placeholder="Edition" name="bookEdition" onChange={this.handleChange}/><br/>
                        <input className="form-control" type="text" placeholder="Availability" name="bookAvailability" onChange={this.handleChange}/><br/>
                        <input className="form-control" type="text" placeholder="Induction Date" name="bookInductionDate" onChange={this.handleChange}/><br/>
                        <button className="btn btn-primary" onClick={this.handleSubmit}>Add</button>
                    </form>
                </SkyLight>
                <div>
                    <button className="add-btn btn btn-primary" style={{'margin': '10px 0'}}
                            onClick={() => this.refs.addDialog.show()}>Add Book
                    </button>
                </div>
            </div>
        )
    }


}

export default AddBook;
