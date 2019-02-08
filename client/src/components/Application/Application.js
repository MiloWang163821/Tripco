import React, {Component} from 'react';
import { Container, Card, CardBody, Row, Col } from 'reactstrap';
import Info from './Info'
import Options from './Options';
import Plan from './Plan';
import Map from './Map';
import Search from './Search';
import Add from './Add';
import Attribute from './Attribute';
import { get_config } from '../../api/api';
import Calculator from "./Calculator";
import Credits from "./Credits";


/* Renders the application.
 * Holds the destinations and options state shared with the trip.
 */
class Application extends Component {
    constructor(props){
        super(props);
        this.state = {
        config: null,
        trip: {
            type: "trip",
            title: "",
            options : {
                units: "miles",
                unitName: "miles",
                unitRadius: 3959,
                optimization: "none",
                map: "svg"
            },
            places: [],
            distances: [],
            map: ''
        },
        search : {
            version: 5,
            type: "search",
            match: "",
            filter: {
                name:"",
                values:[]
            },
            limit: 10,
            found: 0,
            places: []
        },
        selectAll: false,
        selected: new Map(),
        table : {
            id: false,
            name: true,
            latitude: false,
            longitude: false,
            next: true,
            total: true,
        }
        };
        this.updateTrip = this.updateTrip.bind(this);
        this.updateBasedOnResponse = this.updateBasedOnResponse.bind(this);
        this.updateOptions = this.updateOptions.bind(this);
        this.updateOpts = this.updateOpts.bind(this);
        this.clearPlace = this.clearPlace.bind(this);
        this.updateSearch = this.updateSearch.bind(this);
        this.re_render = this.re_render.bind(this);
        this.addPlace = this.addPlace.bind(this);
        this.updateMapOpts = this.updateMapOpts.bind(this);
        this.updateTable = this.updateTable.bind(this);
        this.updateSelected = this.updateSelected.bind(this);
        this.updateSelectAll = this.updateSelectAll.bind(this);
    }

    componentWillMount() {
        get_config().then(
        config => {
            this.setState({
            config:config
            })
        }
        );
    }

  re_render(){
      let trip = this.state.trip;
      this.setState(trip);
  }

  updateSelected(value){
    this.setState({selected: value});
  }

  updateSelectAll(value){
    this.setState({selectAll: value});
  }

  updateTable(value){
    let table = this.state.table;
    table[value] = !table[value];
    this.setState({table: table});
    this.updateSelectAll(false);
    }

    updateTrip(field, value){
        let trip = this.state.trip;
        trip[field] = value;
        this.setState(trip);
    }

    updateBasedOnResponse(value) {
        if(value.trip)
        {
            this.setState({'trip': value.trip});
        }
        else
        {
            this.setState({'trip': value});
        }

        if(value.config)
        {
            this.setState({'config': value.config});
        }

    }

    updateSearch(value){
        this.setState({'search': value});
    }


  updateMapOpts(optionMem, value)
  {
    let trip = this.state.trip;
    console.log("updating map to "+value);
    trip.options.map = value;
    this.setState(trip);
  }

  updateOpts(optionMem, valueL) {
        let trip = this.state.trip;
        console.log(valueL);

        trip.options.optimization = valueL;

        this.setState(trip);
        console.log(this.state.trip.options.optimization);
  }

  updateOptions(optionMem, value) {
      let trip = this.state.trip;
      if (optionMem === "units") {
          if (value != "user defined") {
                trip.options[optionMem] = value;
          }
          else {//make a popup that takes two values and puts them in the two fields
          //this is the name of the user difined units and the earths radius in that unit
              trip.options.units = "user defined";
              trip.options.unitName = prompt("Please enter your desired unit type:", "feet");
              trip.options.unitRadius = prompt("Please enter the radius of the earth in your unit:", "20903520");
          }
      }
      this.setState(trip);
  }

  clearPlace(){
      //while(!trip.places.isEmpty()){
      let trip = this.state.trip;
      trip.places = [];
      trip.map = ""; //map is no longer accurate, so invalidate it
      trip.distances = []; //distances are also now false, so invalidate them.
      this.setState(trip);
      //}
  }

  addPlace(place) {
    let trip = this.state.trip;
    trip.places.push(place);
    trip.distances = [];
    trip.map = "";
    this.setState(trip);
  }

  render() {
    if(!this.state.config) { return <div/> }

    return(
      <Container id="Application">
        <Info/>
        <Row xs="auto">
            <Plan updateBasedOnResponse={this.updateBasedOnResponse} trip={this.state.trip} clearPlace={this.clearPlace}/>
            <Search trip={this.state.trip} places={this.state.trip.places} search={this.state.search} updateSearch={this.updateSearch} addPlace={this.addPlace} config={this.state.config}/>
            <Add places={this.state.trip.places} distances={this.state.trip.distances} trip={this.state.trip} updateTrip={this.updateTrip}/>
        </Row>
        <Options updateOpts={this.updateOpts} trip={this.state.trip} updateBasedOnResponse={this.updateBasedOnResponse} config={this.state.config} updateOptions={this.updateOptions} updateMapOpts={this.updateMapOpts}/>
        <Map image={this.state.trip.map} />
             <Attribute 
             table={this.state.table} distances={this.state.trip.distances} trip={this.state.trip} places={this.state.trip.places} 
             updateTable={this.updateTable} selectAll={this.state.selectAll} selected={this.state.selected}
             updateSelectAll={this.updateSelectAll} updateSelected={this.updateSelected} updateTrip={this.updateTrip}/>
        {/*<Calculator distances={this.state.trip.distances} trip={this.state.trip} places={this.state.trip.places} config={this.state.config} updateOptions={this.updateOptions}/>*/}
        <Credits/>
      </Container>
    )
  }
}

export default Application;
