import './enzyme.config.js'                   // (1)
import React from 'react'
import { mount } from 'enzyme'              // (2)
import AttributeTable from '../src/components/Application/AttributeTable'
import Map from '../src/components/Application/Map'

test('default build', () => {
    <AttributeTable />
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

  let a = new AttributeTable({})
  a.props.trip = tripy
  a.props.table = tabley
  a.props.selected = new Map()
  a.props.updateTrip = (a, b) => {}
  a.props.updateSelectAll = (a, b) => {}
  a.props.updateSelected = (a) => {}

  a.table()

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

  let a = new AttributeTable({})
  a.props.trip = tripy
  a.props.table = tabley
  a.props.selected = new Map()

  a.table()
});

test('table branch cleanup', () => {
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
        "distances": []
  }
  const table1 = {
            id: false,
            name: true,
            latitude: true,
            longitude: true,
            next: true,
            total: true
  }
  const table2 = {
            id: false,
            name: false,
            latitude: true,
            longitude: true,
            next: true,
            total: true
  }
  const table3 = {
            id: false,
            name: false,
            latitude: false,
            longitude: true,
            next: true,
            total: true
  }
  const table4 = {
            id: false,
            name: false,
            latitude: false,
            longitude: false,
            next: true,
            total: true
  }
  const table5 = {
            id: false,
            name: false,
            latitude: false,
            longitude: false,
            next: false,
            total: false
  }
  const table6 = {
            id: false,
            name: false,
            latitude: false,
            longitude: false,
            next: false,
            total: false
  }

  let a = new AttributeTable({})
  a.props.trip = tripy
  a.props.selected = new Map()

  a.props.table = table1
  a.table()

  a.props.table = table2
  a.table()

  a.props.table = table3
  a.table()

  a.props.table = table4
  a.table()

  a.props.table = table5
  a.table()

  a.props.table = table6
  a.table()

  a.props.table.next = false
  a.props.table.total = false
  a.props.trip.distances = [1,2,3,4,25]

  a.table()

  const tripy2 = {"title": "World Map Test Case",
	"options": {},
	"places": [
	],
        "distances": []
  }

  a.props.trip = tripy2
 
  a.props.table = table1
  a.table()

  a.props.table = table2
  a.table()

  a.props.table = table3
  a.table()

  a.props.table = table4
  a.table()

  a.props.table = table5
  a.table()

  a.props.table = table6
  a.table()


  a.props.table.next = false
  a.props.table.total = false

  a.table()
});





