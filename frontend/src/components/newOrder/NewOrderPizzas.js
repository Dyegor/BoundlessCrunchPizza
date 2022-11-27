import { Button } from 'reactstrap'

export default function NewOrderPizzas({pizzaList, setPizzaList}) {

  const removePizzaFromOrder = (index) => {
    const newPizzaList = [...pizzaList];
    newPizzaList.splice(index, 1)
    setPizzaList(newPizzaList)
  }

  return (
    <>
      {pizzaList.map((pizza, index) => (
        <tr>
          <td>{pizza.pizzaSize.pizzaSize}</td>
          <td>{pizza.pizzaBase.pizzaBaseName}</td>
          <td>{pizza.topping.toppingName}</td>
          <td>
            <Button color="danger" onClick={() => removePizzaFromOrder(index)}>
              Remove
            </Button>
          </td>
        </tr>
      ))}
    </>
  )
}
