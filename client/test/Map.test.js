import './enzyme.config.js'                   // (1)
import React from 'react'
import { mount } from 'enzyme'              // (2)
import Map from '../src/components/Application/Map'

test('check default', () => {
  <Map />
});

test('check constructor', () => {
  let m = new Map({})
});

test('checking funcs', () => {
  let m = new Map({})
  m.props.image = ''
  m.render()
  m.toggleVisibility()
  
  m.props.image = '<svg width="1920" height="20" xmlns="http://www.w3.org/2000/svg" xmlns:svg="http://www.w3.org/2000/svg"><g></g></svg>'
  m.render()
  
  m.props.image= 'hello there'
  m.render()
  
  m.state = {}
  m.state.visible = false
  m.render()
});