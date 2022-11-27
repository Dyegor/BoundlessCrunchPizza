import React, { Component } from 'react'
import './App.css'
import Home from './components/Home'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import NewOrder from './components/newOrder/NewOrder'
import OrdersList from './components/existingOrder/OrdersList'

export default class App extends Component {
	render() {
		return (
			<BrowserRouter>
				<Routes>
					<Route exact path='/' element={<Home/>} />
					<Route path='/orders' element={<OrdersList/>} />
					<Route path='/createOrders' element={<NewOrder/>} />
				</Routes>
			</BrowserRouter>
		)
	}
}
