import React, {Component} from "react";
import {request} from "../../api/api";
import { button, Table, Col, Card, CardBody, Button } from "reactstrap";

class Search extends Component {

    constructor(props){
        super(props);
        let f = [];
        for(let i = 0; i < this.props.config.filters.length; i++){
            let filterElement = {
                name:this.props.config.filters[i].name,
                values:[]
            }
            f.push(filterElement)
        }
        this.state = {
            filters: f,
            visible: true
        }
        this.searchBar = this.searchBar.bind(this);
        this.searchButton = this.searchButton.bind(this);
        this.displayTable = this.displayTable.bind(this);
        this.check = this.check.bind(this);
        this.toggleVisibility = this.toggleVisibility.bind(this);
        this.table = this.table.bind(this);
    }

    searchBar(event){
        let value = event.target.value;
        let searchBody = {
            version: 5,
            type: "search",
            match: value,
            filters: this.state.filters,
            limit: 0,
            found: 0,
            places: []
        }
        this.props.updateSearch(searchBody);
    }

    searchButton(){
        if(this.props.search.match != ""){
            console.log(this.props.search);
            this.props.search.limit = 10;
            request(this.props.search, "search").then((res) =>{
                this.props.updateSearch(res);
                console.log("finding------", this.props.search.found);
                console.log(this.props.search.places);
            });
        }
    }


    displayTable(){
        let table = [];
        if(this.props.search.places.length > 50){
            for(let i = 0; i < 50; i++){
                table.push(<tr>{this.props.search.places[i].name} <button className="addplace" onClick={() => {
                    let placeElement = {
                        id:this.props.search.places[i].id,
                        name:this.props.search.places[i].name,
                        latitude:this.props.search.places[i].latitude,
                        longitude:this.props.search.places[i].longitude
                    }
                    this.props.addPlace(placeElement)
                }
                } type="button">+</button> </tr>);
            }
        }
        else if(this.props.search.places.length <= 50 && this.props.search.places.length > 0){
            for(let i = 0; i < this.props.search.places.length; i++){
                table.push(<tr>{this.props.search.places[i].name} <button className="addplace" onClick={() => {
                    let placeElement = {
                        id:this.props.search.places[i].id,
                        name:this.props.search.places[i].name,
                        latitude:this.props.search.places[i].latitude,
                        longitude:this.props.search.places[i].longitude
                    }
                    this.props.addPlace(placeElement)
                }
                } type="button">+</button> </tr>);
            }
        }
        else{
            table.push(<tr>{}</tr>);
        }
        return table;
    }


    check(event, name){
        let thisFilters = this.state.filters;
        let checked = event.target.checked;
        let value = event.target.value;

        //console.log(thisFilters);
        for(let i = 0; i < thisFilters.length; i++){
            let index = thisFilters[i].values.indexOf(value);
            if(thisFilters[i].name == name){
                if(checked){
                    thisFilters[i].values.push(value);
                }
                else{
                    thisFilters[i].values.splice(index, 1);
                }
            }
        }
        this.setState({'filters': thisFilters});
    }

    table(){
        let t = [];
        for (let i = 0; i < this.props.config.filters.length; i++){
            for(let j = 0; j < this.props.config.filters[i].values.length; j++){
                t.push(<form><input onChange={(e) => {
                    this.check(e, this.props.config.filters[i].name)
                }
                } type="checkbox" value={this.props.config.filters[i].values[j]} />{this.props.config.filters[i].values[j]}</form>);
            }
        }
        return t;
    }


    toggleVisibility(){
        let vis = this.state.visible;
        this.setState({visible: !vis});
        console.log(this.state);
    }

    render(){

        if (this.state.visible) {
        if(this.props.search.found != 0) {
            return (
                <Col sm={6} md={4} style={{ overflow: 'overlay', height: '280px'}}>
                    <Card>
                        <CardBody>
                            <div>
                               <form>
                                    <Button className="searchbutton" onClick={this.searchButton} type="button" color="primary">Search</Button>

                                    <input type="text" placeholder="Search..." ref="filterTextInput"  onChange={this.searchBar}/>
                                    <p>Found {this.props.search.found} results</p>
                                    <Table className="table" responsive>
                                        <tbody>{this.displayTable()}</tbody>
                                    </Table>

                                    {this.table()}

                                    <p><Button onClick={this.toggleVisibility}>Hide Search</Button></p>
                                </form>
                            </div>
                        </CardBody>
                    </Card>
                </Col>
            );
        }
        else {
             return (
                <Col sm={6} md={4} style={{ overflow: 'overlay', height: '280px'}}>
                    <Card>
                        <CardBody>
                            <div>
                               <form>
                                    <Button className="searchbutton" onClick={this.searchButton} type="button" color="primary">Search</Button>

                                    <input type="text" placeholder="Search..." ref="filterTextInput"  onChange={this.searchBar}/>
                                    <Table className="table" responsive>
                                        <tbody>{this.displayTable()}</tbody>
                                    </Table>

                                    {this.table()}

                                    <p><Button onClick={this.toggleVisibility}>Hide Search</Button></p>
                                </form>
                            </div>
                        </CardBody>
                    </Card>
                </Col>
            );
        }
        }
        else
        {
            return (
               <div>
                <Col sm={6} md={4}>
                <Button onClick={this.toggleVisibility}> Show Search </Button>
                </Col>
               </div>
            )
        }
    }


}
export default Search;

