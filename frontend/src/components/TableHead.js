export default function TableHead({columns}) {

  return (
    <thead>
      <tr>
        {columns.map((column) => (
          <th key={column.key}>
            {column.label}
          </th>
         ))}
      </tr>
    </thead>
  )
}
