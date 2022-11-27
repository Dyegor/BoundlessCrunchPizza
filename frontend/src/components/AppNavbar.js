import React from 'react'
import { Navbar, NavbarBrand } from 'reactstrap'
import { Link } from 'react-router-dom'

export default function AppNavbar() {
  return (
    <Navbar color="light" expand="md">
      <NavbarBrand style={{ fontSize: 15, marginLeft: 5 }} tag={Link} to="/">Home Page</NavbarBrand>
    </Navbar>
  )
}
