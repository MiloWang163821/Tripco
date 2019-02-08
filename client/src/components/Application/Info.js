import React, {Component} from 'react'
import {Card, CardHeader, CardBody} from 'reactstrap'
import {button, Button} from 'reactstrap'

export default class Info extends Component {
  constructor(props) {
    super(props)
    this.state = {
      visible: true
    }

    this.toggleVisibility = this.toggleVisibility.bind(this);
  }

  toggleVisibility(){
        let vis = this.state.visible;
        this.setState({visible: !vis});
        console.log(this.state);
  }
  
  render() {
    if (this.state.visible)
    {
    return (
      <div>
        <Card>
          <CardBody>
            <p className="lead">"Want to travel far and wide?" <Button onClick={this.toggleVisibility}>  Hide</Button></p>
            <ol >
              <li>
                Choose options for trip planning, information to display about locations,
                and how the trip map and itinerary should be saved.</li>
              <li>
                Choose your destinations by loading existing sets of destinations or
                find more in an extensive database of locations worldwide.</li>
              <li>
                Plan the trip with the options you selected.
                Review and revise the trip origin and order.
                Save the trip map and itinerary for future reference.</li>
            </ol>
          </CardBody>
        </Card>
      </div>
    )
    }
    else
    {
      return (
                <div>
                    <Button onClick={this.toggleVisibility}> Show Info </Button>
                </div>
            )
    }
  }
}