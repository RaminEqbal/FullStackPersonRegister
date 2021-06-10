import HeadBar from "./components/HeadBar.component";
import PersonTable from "./components/PersonTable.component";


import "./css/navbar.css"
import "./css/base.css"
import "./css/form.css"
import { localeStrings } from "./globals";



const setLocale = localeStrings.german;




/**
 *
 * Main Entry Point of the React App
 */
function App() {
  return (
    <div className="App">
      <HeadBar title={setLocale.html.APPLICATION_TITLE} />
      <PersonTable />
    </div>
  );
}

export default App;
