import React, {Component} from 'react';
import { Card, CardBody, Col} from 'reactstrap';
import { Button } from 'reactstrap';
import { Table } from 'reactstrap';
import AttributeTable from './AttributeTable';

class Attribute extends Component{
    constructor(props){
        super(props);
        this.state = {
            visible: true
        };

        //this.table = this.table.bind(this);
        this.setNewStart = this.setNewStart.bind(this);
        this.deletePlace = this.deletePlace.bind(this);
        this.reverseTrip = this.reverseTrip.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.handleAll = this.handleAll.bind(this);
        this.toggleVisibility = this.toggleVisibility.bind(this);
    }

    setNewStart(){
        let place = this.props.trip.places;
        let distance = this.props.trip.distances;
        var index = place.length;
        while(index--){
            if(this.props.selected[place[index].id]===true){
                place.splice(0, 0, place[index]);
                place.splice(index+1, 1);
                const selected = Object.assign({}, this.props.selected);
                selected[place[0].id] = !this.props.selected[place[0].id];
                this.props.updateSelected(selected);
                break;
            }
        }
        distance.push(0);
        this.props.updateTrip('places', place);
    }

    deletePlace(){
        if(this.props.selectAll===true){
            this.props.updateTrip('places', []);
        }
        else{
            let place = this.props.trip.places;
            var index = place.length;
            while(index--){
                if(this.props.selected[place[index].id]===true){
                    place.splice(index, 1);
                }
            }
        this.props.updateTrip('places', place);
        }
    }

    handleAll(){
        let selected={};
        if (this.props.selectAll===false){
            this.props.trip.places.forEach(place => {
                selected[place.id]=true;});
        }
        this.props.updateSelectAll(this.props.selectAll===false);
        this.props.updateSelected(selected);
    }

    handleChange(value){
        const selected = Object.assign({}, this.props.selected);
        selected[value] = !this.props.selected[value];
        this.props.updateSelected(selected);
    }

    

    reverseTrip(){
        this.props.updateTrip('places', this.props.trip.places.reverse());
    }

    toggleVisibility(){
        let vis = this.state.visible;
        this.setState({visible: !vis});
    }
    
    render(){
        if (this.state.visible)
        {
        return(
            <div style={{ overflow: 'auto'}}>
            <Card>
                <CardBody>
                        <p><Button onClick={this.toggleVisibility}>Hide Itinerary</Button></p>
                        <pre>
                        <label>ID: 
                            <input type="checkbox" checked={this.props.table.id} value={'id'} onChange={event=>{this.props.updateTable(event.target.value)}}/>
                        </label>
                        <label> Name: 
                            <input type="checkbox" checked={this.props.table.name} value={'name'} onChange={event=>{this.props.updateTable(event.target.value)}}/>
                        </label>
                        <label> Latitude: 
                            <input type="checkbox" checked={this.props.table.latitude} value={'latitude'} onChange={event=>{this.props.updateTable(event.target.value)}}/>
                        </label>
                        <label> Longitude: 
                            <input type="checkbox" checked={this.props.table.longitude} value={'longitude'} onChange={event=>{this.props.updateTable(event.target.value)}}/>
                        </label>
                        <label> Next Distance: 
                            <input type="checkbox" checked={this.props.table.next} value={'next'} onChange={event=>{this.props.updateTable(event.target.value)}}/>
                        </label>
                        <label> Total Distance: 
                            <input type="checkbox" checked={this.props.table.total} value={'total'} onChange={event=>{this.props.updateTable(event.target.value)}}/>
                        </label>
                        </pre>
                            <AttributeTable selected={this.props.selected} trip={this.props.trip} table={this.props.table} selectAll={this.selectAll} handleAll={this.handleAll} handleChange={this.handleChange} />
                            <Button color="primary" onClick={this.setNewStart}>SetStart</Button>
                            <Button color="danger" onClick={this.deletePlace}>Remove</Button>
                            <Button color="success" onClick={this.reverseTrip}>Reverse</Button>
                </CardBody>
            </Card>
            </div>

        );
        }
        else
        {
            return (
                <div>
                    <Button onClick={this.toggleVisibility}> Show Itinerary </Button>
                </div>
            )
        }
    }
}

export default Attribute;
