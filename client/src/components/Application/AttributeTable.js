import React, {Component} from 'react';
import { Card, CardBody, Col} from 'reactstrap';
import { Button } from 'reactstrap';
import { Table } from 'reactstrap';

class AttributeTable extends Component{
    constructor(props) {
        super(props);
        this.table = this.table.bind(this);
        this.columns = this.columns.bind(this);
    }

    columns(){
        let content = [];
        if(this.props.table.id===true){
            content.push(<th>{"ID"}</th>);
        }
        if(this.props.table.name===true){
            content.push(<th>{"Name"}</th>);
        }
        if(this.props.table.latitude===true){
            content.push(<th>{"Latitude"}</th>);
        }
        if(this.props.table.longitude===true){
            content.push(<th>{"Longitude"}</th>);
        }
        if(this.props.table.next===true){
            content.push(<th>{"Next distance"}</th>);
        }
        if(this.props.table.total===true){
            content.push(<th>{"Total distance"}</th>);
        }
        return content;
    }

    table(){
        let table = [];
        let content = [];
        let element = [];
        let total = 0;
        //defualt table
        if(this.props.trip.places.length===0){
            if(this.props.table.id===true){
                content.push(<th>{"ID"}</th>);
            }
            if(this.props.table.name===true){
                content.push(<th>{"Name"}</th>);
            }
            if(this.props.table.latitude===true){
                content.push(<th>{"Latitude"}</th>);
            }
            if(this.props.table.longitude===true){
                content.push(<th>{"Longitude"}</th>);
            }
            if(this.props.table.next===true){
                content.push(<th>{"Next distance"}</th>);
            }
            if(this.props.table.total===true){
                content.push(<th>{"Total distance"}</th>);
            }
            table.push(<tr>{content}</tr>);
            return table
        } 
        //select all places at once
        else{
            if(this.props.table.id===true || this.props.table.name===true || this.props.table.latitude===true || this.props.table.longitude===true || this.props.table.next===true || this.props.table.total===true){
                content.push(
                <th>
                    <form>
                        <input type="checkbox" checked={this.props.selectAll} value={this.props.selectAll} onChange={this.props.handleAll}/>
                    </form>
                </th>
                );
            }
            if(this.props.table.id===true){
                content.push(<th>{"ID"}</th>);
            }
            if(this.props.table.name===true){
                content.push(<th>{"Name"}</th>);
            }
            if(this.props.table.latitude===true){
                content.push(<th>{"Latitude"}</th>);
            }
            if(this.props.table.longitude===true){
                content.push(<th>{"Longitude"}</th>);
            }
            if(this.props.table.next===true){
                content.push(<th>{"Next distance"}</th>);
            }
            if(this.props.table.total===true){
                content.push(<th>{"Total distance"}</th>);
            }
            table.push(<tr>{content}</tr>);
            //select places individually
            for (let i = 0; i < this.props.trip.places.length; i++){
                element = [];
                if(this.props.table.id===true || this.props.table.name===true || this.props.table.latitude===true || this.props.table.longitude===true || this.props.table.next===true || this.props.table.total===true){
                    element.push(
                    <th>
                        <form>
                            <input type="checkbox" name="checkOne" id={"checkOne" + i} checked={this.props.selected[this.props.trip.places[i].id]} value={this.props.trip.places[i].id} onChange={(event) => {this.props.handleChange(event.target.value)}}/>
                        </form>
                    </th>
                    );
                }
                if(this.props.table.id===true){
                    element.push(<th>{this.props.trip.places[i].id}</th>);
                }
                if(this.props.table.name===true){
                    element.push(<th>{this.props.trip.places[i].name}</th>);
                }
                if(this.props.table.latitude===true){
                    element.push(<th>{this.props.trip.places[i].latitude}</th>);
                }
                if(this.props.table.longitude===true){
                    element.push(<th>{this.props.trip.places[i].longitude}</th>);
                }
                if(this.props.trip.distances.length===0){
                    if(this.props.table.next===true){
                        element.push(<th>{'0'}</th>);
                    }
                    if(this.props.table.total===true){
                        element.push(<th>{'0'}</th>);
                    }
                }
                else{
                    if(this.props.table.next===true){
                        element.push(<th>{this.props.trip.distances[i]}</th>);
                    }
                    total = total + this.props.trip.distances[i];
                    if(this.props.table.total===true){
                        element.push(<th>{total}</th>);
                    }
                }
                table.push(<tr>{element}</tr>);
            }
            return table;
        }
    }

    render() {
        if(this.props.trip.places.length > 10)
        {
            return(
               <Col style= {{ overflow: 'overlay', height: '600px' }}>
                    <Table useFixedHeader={true} columns={this.columns()}>
                        <tbody>{this.table()}</tbody>
                    </Table>
                </Col>
            );
        }
        else
        {
            return(
               <Col>
                    <Table

                    >
                        <tbody>{this.table()}</tbody>
                    </Table>
                </Col>
            )
        }
    }





}

export default AttributeTable;
