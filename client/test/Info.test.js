import './enzyme.config.js'                   // (1)
import React from 'react'
import { mount } from 'enzyme'              // (2)
import Info from '../src/components/Application/Info'

test('check default', () => {
  <Info />
});

test('check constructor', () => {
  let i = new Info({})
});

test('checking funcs', () => {
  let i = new Info({})
  
  i.render()
  i.toggleVisibility()
  i.render()
  i.render()
  i.toggleVisibility()
  i.state = {}
  i.state.visible = false
  i.render()
});