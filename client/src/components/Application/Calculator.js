import React, {Component} from "react";
import {request} from '../../api/api.js'
import {button, Button, ButtonGroup} from 'reactstrap'
import { Table, Col, Row, Card, CardBody, FormGroup } from "reactstrap";


class Calculator extends Component {

    constructor(props){
        super(props);
        this.state = {
            distances : {
                type : "distance",
                version : 5,
                unitName : "",
                unitRadius : 0.0,
                origin : {
                    latitude : "",
                    longitude : "",
                },
                destination : {
                    latitude : "",
                    longitude : "",
                },
                units : "miles",
                distance : 0
            },
            visible: true
        }
        this.updatePlace = this.updatePlace.bind(this);
        this.updateDistance = this.updateDistance.bind(this);
        this.calculate = this.calculate.bind(this);
        this.displayResult = this.displayResult.bind(this);
        this.handleClick = this.handleClick.bind(this);
        this.toggleVisibility = this.toggleVisibility.bind(this);
    }

    updatePlace(field, value, origin){
        if(origin===false){
            let distances = this.state.distances.origin;
            distances[field] = value;
            this.setState(distances);
        }
        else{
            let distances = this.state.distances.destination;
            distances[field] = value;
            this.setState(distances);
        }

    }

    updateDistance(field, value){
        let temp = this.state.distances;
        temp[field] = value;
        this.setState(temp);
    }

    calculate(){
        request(this.state.distances,'distance').then((res)=>
        {
            this.updateDistance('distance',res.distance);
        });
    }

    displayResult(){
        if(this.state.distances.unitName!=="")
            return this.state.distances.unitName;
        else
            return this.state.distances.units;
    }

    handleClick(event){
        this.updateDistance('units', event.target.value);
    }

    toggleVisibility(){
        let vis = this.state.visible;
        this.setState({visible: !vis});
    }

    render(){
        if (this.state.visible)
        {
        return (
            <div>
            <Row>
            <Col md={6}>
                <Card>
                    <CardBody>
                        <p><Button onClick={this.toggleVisibility}>Hide Calculator</Button></p>
                                <p><label>Origin:
                                    <input type="text" onChange={(event) => this.updatePlace('latitude', event.target.value, false)}/>
                                    <input type="text" onChange={(event) => this.updatePlace('longitude', event.target.value, false)}/>
                                </label></p>
                                <p><label>Destination:
                                    <input type="text" onChange={(event) => this.updatePlace('latitude', event.target.value, true)}/>
                                    <input type="text" onChange={(event) => this.updatePlace('longitude', event.target.value, true)}/>
                                </label></p>
                                <Button type={"button"} onClick={this.Calculate}>Calculate</Button>
                                <p>{"Final distance " + this.state.distances.distance + " "}{this.displayResult()}</p>
                        </CardBody>
                    </Card>
                </Col>
                </Row>
        </div>
        );
        }
        else
        {
            return(
                <div>
                    <Button onClick={this.toggleVisibility}> Show Calculator </Button>
                </div>
            )
        }
    }

}
export default Calculator;
