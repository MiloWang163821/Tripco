import './enzyme.config.js'                   // (1)
import React from 'react'
import { mount } from 'enzyme'              // (2)
import Credits from '../src/components/Application/Credits'

test('check default', () => {
  <Credits/>
});

test('actual test', () => {
  let c = new Credits({})

  c.render()
  c.toggleVisibility()
  
  c.state = {}
  c.state.visible = true
  c.render()
});