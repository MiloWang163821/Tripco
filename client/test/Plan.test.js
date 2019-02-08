import './enzyme.config.js'                   // (1)
import React from 'react'
import { mount } from 'enzyme'              // (2)
import Plan from '../src/components/Application/Plan'

const full_trip = {
        "type": "trip",
	"title": "World Map Test Case",
	"options": {},
	"places": [{
                        "id": "bl",
                        "name": "Isla Hornos (SA)",
                        "latitude": -55.9467,
                        "longitude": -67.2751
                },
                {
                        "id": "tl",
                        "name": "Anchorage (AK)",
                        "latitude": 61.2181,
                        "longitude": -149.9003
                },
                {
                        "id": "tr",
                        "name": "Ossora (Russia)",
                        "latitude": 59.2331,
                        "longitude": 163.0675
                },
                {
                        "id": "br",
                        "name": "ChristChurch (New Zealand)",
                        "latitude": -43.5321,
                        "longitude": 172.6362
                },
                {
                        "id":"null",
                        "name":"Null Island",
                        "latitude": 0,
                        "longitude": 0
                }
	],
        "distances":[1,2,3,4,25]
}        

test('default test', () => {
  <Plan />
});

test('default constructor', () => {
  let p = new Plan({})
  p.props.trip = full_trip

  p.saveMap()
  p.props.trip.options = {first: 'hello'}
  p.saveMap()
  p.props.trip.options.map = 'svg'
  p.saveMap()
  p.props.trip.options.map = 'kml'
  p.saveMap()

  p.saveFile()

  p.plan()

  p.props.clearPlace = () => {}
  p.clear()

  p.render()
  p.toggleVisibility()
  p.state = {}
  p.state.visible = false
  p.render()
});


test('expecting failure', () => {
  let p = new Plan({})
  p.props.trip = full_trip
  p.props.updateBasedOnResponse = (a) => {}

  const eventy = {
    target: {
      files: ["used_file.txt", "unusedfile", false]
    }
  }
  
  try{
    p.uploadFile(eventy)
  }
  catch(err)
  { //do nothing
  }
});











