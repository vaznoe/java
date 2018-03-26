import React, { Component } from 'react';

class Signup extends Component {
    constructor(props) {
      super(props);
      this.state = {
        myTextBox: 'Sherlock Holmes',
        myTextArea: 'My address in 221B Baker Street',
        myCheckBox: true,
        mySelectTag: 'Liverpool'
      };

      this.handleChange = this.handleChange.bind(this);
    }

    handleChange(event) {
      const target = event.target;
      const value = target.type === 'checkbox' ? target.checked : target.value;
      const name = target.name;

      this.setState({
        [name]: value.toUpperCase()
      });
    }
    
    render() {
      return (
    <div className="content">
      <fieldset className="form">
        <legend>Please enter your details</legend>
        <form>
          <table>
              <tr>
                  <td>Enter Name</td>
                  <td><input	name="myTextBox" type="text" value={this.state.myTextBox} onChange={this.handleChange} /></td></tr>
              <tr>
                  <td>Enter Address</td>
                  <td><textarea value={this.state.myTextArea} onChange={this.handleChange} /></td></tr>
              <tr>
                  <td>Preferred Language</td>
                  <td>English : <input name="myCheckBox" type="checkbox" checked={this.state.myCheckBox} onChange={this.handleChange}/></td>
                  <td>Spanish : <input name="mySpanishCheckBox" type="checkbox" onChange={this.handleChange}/></td>
              </tr>
              <tr>
                  <td>City</td>
                  <td><select value={this.state.mySelectTag} onChange={this.handleChange}>
                          <option value="London">London</option>
                          <option value="Manchester">Manchester</option>
                          <option value="Liverpool">Liverpool</option>
                       </select>
                  </td>
              </tr>
              <tr>
                  <td><input type="submit" name="submit"/></td>
                  <td><input type="reset" name="reset"/></td></tr>
          </table>
        </form>
        </fieldset>
        </div>
      );
    }
  }

  export default Signup;