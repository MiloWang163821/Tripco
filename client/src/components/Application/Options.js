import React, {Component} from 'react'
import {Card, CardHeader, CardBody, Container, Row, Col} from 'reactstrap'
import { ButtonGroup, Button } from 'reactstrap'
import {request} from '../../api/api.js'

//import {saveAs} from 'file-saver';
/* Options allows the user to change the parameters for planning
 * and rendering the trip map and itinerary.
 * The options reside in the parent object so they may be shared with the Trip object.
 * Allows the user to set the options used by the application via a set of buttons.
 */
class Options extends Component{
  constructor(props) {
      super(props);
      this.state = {
          host:"",
          port:"",
          visible: true
      }
 
      this.updateServer = this.updateServer.bind(this);
      this.connectServer = this.connectServer.bind(this);
      this.toggleVisibility = this.toggleVisibility.bind(this);
  }

  updateServer(element, value){
      if(element === "host"){
          this.state.host = value;
      }
      else if(element === "port"){
          this.state.port = value
      }
  }

  connectServer(){
      request(this.props.trip, "plan", this.state.port, this.state.host).then((res) =>{
          this.props.updateBasedOnResponse(res);
      });
  }

  toggleVisibility(){
    let vis = this.state.visible;
    this.setState({visible: !vis});
  }



  render(){
    if (this.state.visible)
    {
    if(!this.props.trip) //default case, in case this.props.trip is null or empty 
    {
      this.props.trip = {
        type: "trip",
        title: "",
        options : {
          units: "miles",
          unitName: "miles",
          unitRadius: 3959,
          optimization: "none"
        },
        places: [],
        distances: [],
        map: ''
      } 
    }
    if (!this.props.trip.options) //defualt case if options is null or empty
    {
      this.props.trip.options = {
          units: "miles",
          unitName: "miles",
          unitRadius: 3959,
          optimization: "none",
          map: "svg"
        }
    }
    if (!this.props.trip.options.units)
    {
      this.props.trip.options.units = "miles";
    }
    if (!this.props.trip.options.map)
    {
      this.props.trip.options.map = "svg";
    }
    if (!this.props.trip.options.optimization)
    {
      this.props.trip.options.optimization = "none";
    }
    if (!this.props.config)
    {
      this.props.config = { 'units': ['miles', 'kilometers'], 'optimization': ['none'] }
    }
    if (!this.props.config.maps)
    {
      this.props.config.maps = ["svg"]
    }

    const buttonsOpt = this.props.config.optimization.map((unit) =>
        <Button
            key={'optimization_button_' + unit.label}
            className='btn-outline-dark unit-button'
            active={this.props.trip.options.optimization === unit.label}
            value={unit.label}
            onClick={(event) => this.props.updateOpts('optimization', event.target.value)}
        >
            {unit.label}
        </Button>
      );
    
    const buttonsMap = this.props.config.maps.map((unit) =>
        <Button
            key={'map_button_'+unit}
            className='btn-outline-dark unit-button'
            active={this.props.trip.options.map === unit}
            value={unit}
            onClick={(event) => this.props.updateMapOpts('map', event.target.value)}
        >
            {unit}
        </Button>
     );



    const buttons = this.props.config.units.map((unit) =>
      <Button
        key={'distance_button_' + unit}
        className='btn-outline-dark unit-button'
        active={this.props.trip.options.units === unit}
        value={unit}
        onClick={(event) => this.props.updateOptions('units', event.target.value)}
      >
        {unit.charAt(0).toUpperCase() + unit.slice(1)}
      </Button>
    );
    


    return(
      <Card>
        <CardBody>
        <div>
          <Button onClick={this.toggleVisibility}>Hide Options</Button><p/>
          <ButtonGroup vertical>
            {buttons}
          </ButtonGroup>
          <ButtonGroup vertical>
            {buttonsOpt}
          </ButtonGroup>
          <ButtonGroup vertical>
            {buttonsMap}
          </ButtonGroup>
          <ButtonGroup vertical>
            <label><p>Change Server:</p> host:
                <input type="text" placeholder="black-bottle.cs.colostate.edu" onChange={(event) => this.updateServer("host", event.target.value)}/>
            </label>
            <label>port:
                <input type="text" placeholder="31400" onChange={(event) => this.updateServer("port", event.target.value)}/>
            </label>
            <Button onClick={this.connectServer} color="primary">Connect</Button>
          </ButtonGroup>
        </div> 
        </CardBody>
      </Card>
    )
    }
    else
    {
      return (
        <div>
          <Button onClick={this.toggleVisibility}> Show Options </Button>
        </div>
      )
    }
  }
}

export default Options;
