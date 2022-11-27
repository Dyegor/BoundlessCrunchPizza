import React, { useState, useEffect } from 'react'
import { Container, Table } from 'reactstrap'
import AppNavbar from '../AppNavbar'
import OrdersTableBody from './OrdersTableBody'
import existingOrderColumnsData from '../columns/ExistingOrderColumnsData'
import TableHead from '../TableHead'

export default function OrdersList() {

  const [error, setError] = useState('')
  const [orders, setOrders] = useState([])

  useEffect(() => {
    fetch("/pizza/orders")
      .then(response => response.json())
      .then(data => setOrders(data))
      .catch((error) => setError(error.message))
  }, [])

  if (error) return <h1>{error}</h1>
  if (orders.length === 0) return <h1>No orders stored in the database</h1>

	return (
		<div>
			<AppNavbar />
			<Container fluid>
				<h1 className="row justify-content-center">Boundless Crunch Pizza Orders:</h1>
				<Table striped bordered hover>
          <TableHead columns={existingOrderColumnsData} />
					<OrdersTableBody orders={orders}/>
				</Table>
			</Container>
		</div>
	)
}
