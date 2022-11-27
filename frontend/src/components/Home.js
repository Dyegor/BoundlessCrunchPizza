import React from 'react'
import '../App.css'
import AppNavbar from './AppNavbar'
import { Link } from 'react-router-dom'
import { Button, Container } from 'reactstrap'

export default function Home() {

  return (
    <div>
      <AppNavbar />
      <Container fluid>
        <h1 className="row justify-content-center">Welcome to Boundless Crunch Pizza!</h1>
        <Button color="link"><Link to="/createOrders">Create New Order</Link></Button>
        <Button color="link"><Link to="/orders">View Orders</Link></Button>
      </Container>
    </div>
  );
}
