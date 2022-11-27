import OrdersTableBodyData from './OrdersTableBodyData'

export default function OrdersTableBody({orders}) {

  return (
    <tbody>
      {orders.map((order, index) => (<OrdersTableBodyData key={index} order={order}/>))}
    </tbody>
  )
}
