import '../../App.css'

export default function PizzaDetails({pizza}) {
  return (
    <div>
      Size: {pizza.pizzaSize.pizzaSizeName}, Base: {pizza.pizzaBase.pizzaBaseName}, Topping: {pizza.topping.toppingName}
    </div>
  )
}
