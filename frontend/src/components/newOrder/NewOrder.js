import React, { useState, useEffect } from 'react'
import { Button, Form, FormGroup, Input, Label, Container, Table, Row, Col } from 'reactstrap'
import AppNavbar from '../AppNavbar'
import newOrderColumnsData from '../columns/NewOrderColumnsData'
import NewOrderPizzas from './NewOrderPizzas'
import TableHead from '../TableHead'

export default function NewOrder() {
  const [error, setError] = useState('')

  const [sizes, setSizes] = useState([]);
  const [bases, setBases] = useState([]);
  const [toppings, setToppings] = useState([]);

  const [baseId, setBaseId] = useState('');
  const [sizeId, setSizeId] = useState('');
  const [toppingId, setToppingId] = useState('');

  const [pizzaList, setPizzaList] = useState([]);
  const [customerName, setCustomerName] = useState('');
  const [customerAddress, setCustomerAddress] = useState('');

  const [message, setMessage] = useState(null);

  const createOrder = () => {
    const data = {
      pizzaRequestDtoList: pizzaList,
      customerName: customerName,
      customerAddress: customerAddress
    };

    const requestOptions = {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(data)
    };

    fetch("/pizza/orders", requestOptions)
      .then(response => {
        response.json()
        setMessage("Order successfully submitted!")
        setPizzaList([])
      })
      .catch(err => {
        setMessage(err.message);
      })
  }

  const addPizza = () => {
    const size = sizes.find((size) => size.pizzaSizeId === sizeId);
    const base = bases.find((base) => base.pizzaBaseId === baseId);
    const topping = toppings.find((topping) => topping.toppingId === toppingId);

    const newPizza = {
      pizzaSize: size,
      pizzaBase: base,
      topping: topping,
    };

    setPizzaList(pizzaList => [...pizzaList, newPizza]);
    setMessage('');
  }

  const isPizzaComponentsChosen = () => {
    return toppingId === '' || sizeId === '' || baseId === '';
  }

  const isCredentialsSet = () => {
    return customerName === '' || customerAddress === '';
  }

  const getPizzaComponents = async() => {
    await fetch("/pizza/toppings")
      .then(response => response.json())
      .then(data => setToppings(data))
      .catch((error) => setError(error.message))

    await fetch("/pizza/bases")
      .then(response => response.json())
      .then(data => setBases(data))
      .catch((error) => setError(error.message))

    await fetch("/pizza/sizes")
      .then(response => response.json())
      .then(data => setSizes(data))
      .catch((error) => setError(error.message))
  }

  useEffect(() => {
    getPizzaComponents()
  }, [])

  if (error) return <h1>{error}</h1>

  return (
    <div>
      <AppNavbar />
      <Container fluid>
        <h1 className="row justify-content-center">Create your Boundless Crunch Pizza order:</h1>
          <Table bordered hover>
            <TableHead columns={newOrderColumnsData} />
            <tbody>
              <NewOrderPizzas pizzaList={pizzaList} setPizzaList={setPizzaList}/>
              <tr>
                <td>
                  <Input className="mb-3" type="select" onChange={(event) => setSizeId(parseInt(event.target.value))}>
                    <option selected disabled hidden>Please choose your pizza size</option>
                    {sizes.map((size) => {return <option value={size.pizzaSizeId}>{size.pizzaSize}</option>})}
                  </Input>
                </td>
                <td>
                  <Input className="mb-3" type="select" onChange={(event) => setBaseId(parseInt(event.target.value))}>
                    <option selected disabled hidden>Please choose your pizza base</option>
                    {bases.map((base) => {return <option value={base.pizzaBaseId}>{base.pizzaBaseName}</option>})}
                  </Input>
                </td>
                <td>
                  <Input className="mb-3" type="select" onChange={(event) => setToppingId(parseInt(event.target.value))}>
                    <option selected disabled hidden>Please choose your topping</option>
                    {toppings.map((topping) => {return <option value={topping.toppingId}>{topping.toppingName}</option>})}
                  </Input>
                </td>
                <td>
                  <Button color="warning" disabled={isPizzaComponentsChosen()} onClick={() => addPizza()}>
                    Add To Order
                  </Button>
                </td>
              </tr>
            </tbody>
          </Table>
          <Form>
            <Row>
              <Col md={2}>
                <FormGroup>
                  <Label for="customerName">
                    Name
                  </Label>
                  <Input onChange={(event) => setCustomerName(event.target.value)}
                    id="customerName"
                    name="name"
                    placeholder="Please enter Customer's Name"
                    type="text"
                  />
                </FormGroup>
              </Col>
            </Row>
            <Row>
              <Col md={2}>
                <FormGroup>
                  <Label for="customerAddress">
                    Password
                  </Label>
                  <Input onChange={(event) => setCustomerAddress(event.target.value)}
                    id="customerAddress"
                    name="address"
                    placeholder="Please enter delivery address"
                    type="text"
                  />
                </FormGroup>
              </Col>
            </Row>
            <Button color="danger" disabled={pizzaList.length === 0 || isCredentialsSet()} style={{ marginLeft: 5 }} onClick={() => createOrder()}>
              Submit
            </Button>
          </Form>
        {message && <h3>{message}</h3>}
      </Container>
    </div>
  )
}
