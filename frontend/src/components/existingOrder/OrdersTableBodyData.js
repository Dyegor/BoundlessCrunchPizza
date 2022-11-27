import { useState } from 'react'
import '../../App.css'
import Collapsible from './Collapsible'
import { Button } from 'reactstrap'

export default function OrdersTableBodyData({order}) {
  const [error, setError] = useState('')

  const getDelivered = (orderId) => {
    fetch("/pizza/orders/makeDelivered/" + orderId, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      }
    })
      .then(response => response.json())
      .catch((error) => setError(error.message))

    window.location.reload();
  }

  if (error) return <h1>{error}</h1>

  return (
    <tr key={order.orderId}>
      <td>{order.orderId}</td>
      <td>
        <Collapsible pizzas={order.pizzaResponseDtoList}/>
      </td>
      <td>{order.customerName}</td>
      <td>{order.customerAddress}</td>
      <td>{order.delivered.toString()}</td>
      <td>
        {!order.delivered &&
          <Button color="success" onClick={() => getDelivered(order.orderId)}>
            Delivered
          </Button>
        }
      </td>
    </tr>
  )
}
