import './App.css'
import DepartmentComponent from './assets/component/DepartmentComponent'
import EmployeeComponent from './assets/component/EmployeeComponent'
import FooterComponent from './assets/component/FooterComponent'
import HeaderComponent from './assets/component/HeaderComponent'
import ListDepartmentComponent from './assets/component/ListDepartmentComponent'
import ListingEmployeeComponent from './assets/component/ListingEmployeeComponent'
import {BrowserRouter, Route, Routes} from 'react-router-dom'

function App() {

  return (
    <>
    <BrowserRouter>
      <HeaderComponent />
        <Routes>
          <Route path='/' element = { <ListingEmployeeComponent /> }></Route>
          <Route path='/employees' element = { <ListingEmployeeComponent /> }></Route>
          <Route path='/add-employee' element = { <EmployeeComponent /> }></Route>
          <Route path='/edit-employee/:id' element = { <EmployeeComponent /> }></Route>
          <Route path='/departments' element = { <ListDepartmentComponent />}></Route>
          <Route path='/add-departments' element = { <DepartmentComponent />}></Route>
          <Route path='/edit-department/:id' element = { <DepartmentComponent />}></Route>

        </Routes>
      <FooterComponent />
    </BrowserRouter>
    </>
  )
}

export default App
