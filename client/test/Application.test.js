import './enzyme.config.js'                   // (1)
import React from 'react'
import { mount } from 'enzyme'              // (2)
import Application from '../src/components/Application/Application'

test('default test', () => {
  <Application />
});

test('default constructor', () => {
  let app = new Application({})

  app.state.trip.places = {
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

  app.re_render()
  app.updateSelected(new Map())
  app.updateSelectAll(true)
  app.updateTable('id')

  app.updateTrip('map', '')
  app.updateBasedOnResponse({'trip':{'stuff':'things'} })
  app.updateBasedOnResponse({'stuff':'things'})
  app.updateBasedOnResponse({'config':{'stuff':'things'} })
  app.updateSearch({
            version: 4,
            type: "search",
            match: "",
            filter: {
                name:"",
                values:[]
            },
            limit: 0,
            found: 0,
            places: []
        })
  app.updateMapOpts('unused', 'kml')
  app.updateOpts('unused', 'none')
  app.updateOptions('units', 'miles')
  app.updateOptions('units', 'user defined')
  app.updateOptions('something else', 'hi')
  app.clearPlace()
  app.addPlace({'id':'no', 'name':'Null', 'longitude':'0', 'latitude':'0'})

  app.render()
  app.state = {}
  app.state = {
        config: [5],
        trip: {
            type: "trip",
            title: "",
            options : {
                units: "miles",
                unitName: "miles",
                unitRadius: 3959,
                optimization: "none",
                map: "svg"
            },
            places: [],
            distances: [],
            map: ''
        },
        search : {
            version: 4,
            type: "search",
            match: "",
            filter: {
                name:"",
                values:[]
            },
            limit: 0,
            found: 0,
            places: []
        },
        selectAll: false,
        selected: new Map(),
        table : {
            id: true,
            name: true,
            latitude: true,
            longitude: true,
            next: true,
            total: true,
        }
  }
  app.render()
  
  

  
});