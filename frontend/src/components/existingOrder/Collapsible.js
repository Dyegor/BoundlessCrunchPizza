import { useState } from 'react'
import PizzaDetails from './PizzaDetails'
import { Button } from 'reactstrap'

export default function Collapsible({pizzas}) {
  const [open, setOpen] = useState(false);

  const toggle = () => {
    setOpen(!open);
  };

  return (
    <div>
      <Button color="info" onClick={toggle}>{open ? 'Hide pizzas' : 'Show pizzas'}</Button>
      {open && (
        <div>
          {pizzas.map((pizza, index) => (<PizzaDetails key={index} pizza={pizza}/>))}
        </div>
      )}
    </div>
  );
};
