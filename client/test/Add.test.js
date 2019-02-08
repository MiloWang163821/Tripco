import './enzyme.config.js'                   // (1)
import React from 'react'
import { mount } from 'enzyme'              // (2)
import Add from '../src/components/Application/Add'

test('check default', () => {
  <Add />
});

test('check constructor', () => {
   let a = new Add({})

});

test('testing funcs', () => {
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

   let a = new Add({})
   a.props.trip = tripy
 
   a.props.updateTrip = (a, b) => {}

   a.addPlace()
   a.updatePlace('id', 'hi')
   a.updatePlace('latitude', '15.0')
   a.updatePlace('longitude', '-15.0')
   a.render()
   a.toggleVisibility()
   
   a.render()

   a.state = {}
   a.state.visible = false
   a.render()
});
