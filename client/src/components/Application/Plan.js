import React, {Component} from 'react'
import {Card, CardHeader, CardBody, Col, Row, Container} from 'reactstrap'
import {button, Button} from 'reactstrap'
import {request} from '../../api/api.js'

export default class Plan extends Component {
    constructor(props) {
        super(props);
        this.state = {
            visible: true
        };

        this.uploadFile = this.uploadFile.bind(this);
        this.saveFile = this.saveFile.bind(this);
        this.plan = this.plan.bind(this);
        this.clear = this.clear.bind(this);
        this.saveMap = this.saveMap.bind(this);
        this.toggleVisibility = this.toggleVisibility.bind(this);
    }

    saveMap() {
        let data = this.props.trip.map;
        let title = this.props.trip.title + '_map';
        if (!this.props.trip.options || !this.props.trip.options.map || this.props.trip.options.map === 'svg')
        {
            title += '.svg';
        }
        else
        {
            title += '.kml'
        }

        let ele = document.createElement('a');
        ele.setAttribute('href', 'data:text/plain;charset=ytf-8,'+encodeURIComponent(data));
        ele.setAttribute('download', title);
        document.body.appendChild(ele);
        ele.click();
        document.body.removeChild(ele);
    }

    saveFile() {
        let jsonData = JSON.stringify(this.props.trip, null, " ");
        let title = this.props.trip.title + ".json";
        let ele = document.createElement("a");
        ele.setAttribute('href', "data:text/plain;charset=ytf-8," + encodeURIComponent(jsonData));
        ele.setAttribute('download', title);
        document.body.appendChild(ele);
        ele.click();
        document.body.removeChild(ele);
    }


    uploadFile(event) {
        let file = event.target.files[0];
        let fileReader = new FileReader();
        fileReader.onload = () => {
            let content = fileReader.result;
            let tffi = JSON.parse(content.toString());
            if(tffi.distances == null){
                tffi.distances = [];
            }
            this.props.updateBasedOnResponse(tffi);
        };
        fileReader.readAsText(file);
    }


    plan(){
        request(this.props.trip, "plan").then((res) =>{
            this.props.updateBasedOnResponse(res);
        });
    }

    clear(){
        this.props.clearPlace();
    }
 
    toggleVisibility(){
        let vis = this.state.visible;
        this.setState({visible: !vis});
    }

    render() {
        if (this.state.visible)
        {
            return (
                <Col sm={6} md={4}>
                <Card>
                    <CardBody>
                        <p><Button onClick={this.toggleVisibility}>Hide Upload</Button></p>
                        <input type="file" className="form-control-file" onChange={this.uploadFile} />
                        <Button color="primary" onClick={this.plan}>Plan</Button>
                        <Button type="button" value="Save Trip" color="success" onClick={this.saveFile}>Save</Button>
                        <Button color="danger" onClick={this.clear}>Clear</Button>
                        <Button color="yellow" onClick={this.saveMap}>Save Map</Button>
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
                    <Button onClick={this.toggleVisibility}> Show Upload </Button>
                    </Col>
                </div>
            )
        }
    }
}
