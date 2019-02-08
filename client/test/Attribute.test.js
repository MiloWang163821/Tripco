import './enzyme.config.js'                   // (1)
import React from 'react'
import { mount } from 'enzyme'              // (2)
import Attribute from '../src/components/Application/Attribute'
import Map from '../src/components/Application/Map'

test('default build', () => {
  <Attribute />
});

test('constructor', () => {
  let a = new Attribute({})
});

test('real test', () => {
  const tripy = {
             'type': "trip",
             'title': "Test trip",
             'options' :
             {
                 'units': "miles",
                 'unitName': "miles",
                 'unitRadius': 3959,
                 'optimization': 'none',
                 'map': 'svg'
             },
             'places': [],
             'distances': [],
             'map': '',
  }
  const tabley = {
            id: true,
            name: true,
            latitude: true,
            longitude: true,
            next: true,
            total: true,
  }

  let a = new Attribute({})
  a.props.trip = tripy
  a.props.table = tabley
  a.props.selected = new Map()
  a.props.updateTrip = (a, b) => {}
  a.props.updateSelectAll = (a, b) => {}
  a.props.updateSelected = (a) => {}

  //a.table()
  a.deletePlace()
  a.setNewStart()
  a.handleAll()
  a.handleChange('id')
  a.reverseTrip()

  a.render()
  a.toggleVisibility()
  a.state = {}
  a.state.visible = false
  a.render()
});

test('non-empty test', () => {
  const tripy = {"title": "World Map Test Case",
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
        "distances": [1,2,3,4,25]
  }

  const tabley = {
            id: true,
            name: true,
            latitude: true,
            longitude: true,
            next: true,
            total: true,
  }

  let a = new Attribute({})
  a.props.trip = tripy
  a.props.table = tabley
  a.props.selected = new Map()
  a.props.selected['tr'] = true;
  a.props.updateTrip = (a, b) => {}
  a.props.updateSelected = (a) => {}
  a.props.selectAll = false
  a.props.updateSelectAll = (a) => {}

  a.setNewStart()

  a.handleAll()

  a.props.selected['br'] = true;
  a.deletePlace()

  a.props.selectAll = true;
  a.deletePlace()
  
});


