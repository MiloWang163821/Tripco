import React, { Component } from "react";
import { Media } from 'reactstrap';
import { Button } from 'reactstrap';
import {Card, CardHeader, CardBody, Col, Row, Container} from 'reactstrap'

export default class Map extends Component {
    constructor(props){
        super(props);
        this.state = {
            visible: true
        };

        this.toggleVisibility = this.toggleVisibility.bind(this);
    }

    toggleVisibility(){
        let vis = this.state.visible;
        this.setState({visible: !vis});
        console.log(this.state);
    }

    render() {
        {
            if (this.state.visible)
            {
            if (this.props.image === '' || this.props.image === '<svg width="1920" height="20" xmlns="http://www.w3.org/2000/svg" xmlns:svg="http://www.w3.org/2000/svg"><g></g></svg>')
            {
                return (<Media></Media>)
            }
            let Header='data:image/svg+xml;utf8,';
            let Data = Header + this.props.image;

            return (
               <div>
                   <Card>
                       <CardBody>
                            <Button onClick={this.toggleVisibility}>Hide</Button>
                       <Media>
		                    <Media body>
		                    <Media object src={Data} alt="image" />
		                    </Media>
	                   </Media>
                       </CardBody>
                   </Card>
               </div>
            )
            }
            else
            {
              return (
                <div>
                    <Button onClick={this.toggleVisibility}> Show Map </Button>
                </div>
              )
            }
        }
    }
}