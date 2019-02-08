import './enzyme.config.js'                   // (1)
import React from 'react'
import { mount } from 'enzyme'              // (2)
import Calculator from '../src/components/Application/Calculator'

test('default test', () => {
  <Calculator />
});

test('default constructor', () => {
  let cal = new Calculator({})
  cal.updatePlace('I','love','lamp')
  cal.updateDistance('ni','hao')
  cal.displayResult()
  cal.handleClick({target: false})
  cal.render()
  cal.calculate()
  cal.toggleVisibility()
  cal.render()

  cal.render()
  cal.toggleVisibility()
  cal.state = {}
  cal.state.visible = false
  cal.render()
});
