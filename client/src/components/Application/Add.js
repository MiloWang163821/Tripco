import React, {Component} from 'react'
import { Card, CardBody, Button, Input, Col, Row, Container } from 'reactstrap'



class Add extends Component{

    constructor(props){
        super(props);
        this.addPlace = this.addPlace.bind(this);
        this.updatePlace = this.updatePlace.bind(this);
        this.toggleVisibility = this.toggleVisibility.bind(this);

        this.state = {
            place: {
                id:"",
                name:"",
                latitude:"0",
                longitude:"0"
            },
            visible: true
        }
    }

    addPlace(){
        let placeElement = {
            id:this.state.place.id,
            name:this.state.place.name,
            latitude:this.state.place.latitude,
            longitude:this.state.place.longitude
        }
        let finalPlace = this.props.trip.places;
        finalPlace.push(placeElement);
        this.props.updateTrip('places',finalPlace);
    }

    updatePlace(element, value){
        let place = this.state.place;
        if(element==='latitude' | element==='longitude'){
            value = parseFloat(value);
            place[element] = value;
        }
        else{
            place[element] = value;
        }
        this.setState(place);
    }

    toggleVisibility(){
        let vis = this.state.visible;
        this.setState({visible: !vis});
    }

    render(){
        if (this.state.visible)
        {
        return(
        <Col sm={6} md={4}>
            <Card>
                <CardBody>
                    <p><Button onClick={this.addPlace} color="primary">Add Place</Button></p>
                    <label>ID:
                    <input type="text" onChange={(event) => this.updatePlace('id', event.target.value)}/>
                    </label>
                    <label>Name:
                    <input type="text" onChange={(event) => this.updatePlace('name', event.target.value)}/> 
                    </label>
                    <label>Latitude:
                    <input type="text" onChange={(event) => this.updatePlace('latitude', event.target.value)}/>                       
                    </label>
                    <label>Longitude:
                    <input type="text" onChange={(event) => this.updatePlace('longitude', event.target.value)}/>          
                    </label>
                    <p><Button onClick={this.toggleVisibility}>Hide Add Place</Button></p>
                </CardBody>
            </Card>
        </Col>
        )
        }
        else
        {
            return (
                <div>
                    <Col sm={6} md={4}>
                    <Button onClick={this.toggleVisibility}> Show Add Place </Button>
                    </Col>
                </div>
            )
        }
    }
}

export default Add;
