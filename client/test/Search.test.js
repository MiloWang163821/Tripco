import './enzyme.config.js'                   // (1)
import React from 'react'
import { mount } from 'enzyme'              // (2)
import Search from '../src/components/Application/Search'

test('default test', () => {
  <Search />
});

test('default constructor', () => { 
  // const searchy = {
  //           version: 4,
  //           type: "search",
  //           match: "",
  //           filter: {
  //               name:"",
  //               values:[]
  //           },
  //           limit: 0,
  //           found: 0,
  //           places: []
  //       }
  // const eventy = {
  //           target: {
  //               value: 'hello there'
  //           }
  // }
  //
  // let s = new Search({})
  // s.props.search = searchy
  // s.props.updateSearch = (a) => {}
  // s.props.search.match = "General Kenobi"
  //
  // s.searchButton()
  // s.displayTable()
  // s.searchBar(eventy)
  //
  // s.props.search.match = "General Kenobi"
  //
  // s.props.search.match = ""
  // s.searchButton()
  //
  //
  // //s.render()
  // s.toggleVisibility()
  // s.state = {}
  // s.state.visible = false
  // s.render()
});


test('display table test', () => {
  //   const searchy = {
  //           version: 4,
  //           type: "search",
  //           match: "",
  //           filter: {
  //               name:"",
  //               values:[]
  //           },
  //           limit: 0,
  //           found: 0,
  //           places: ['a', 'b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s']
  //       }
  //
  // let s = new Search({})
  // s.props.search = searchy
  //
  // s.displayTable()
  //
  // s.props.search.places = ['a', 'b','c','d','e']
  //
  // s.displayTable()

});










