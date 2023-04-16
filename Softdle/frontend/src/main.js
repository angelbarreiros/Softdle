import './app.css'
import App from './App.svelte'
import './state.js'
import './interceptors/axios.js'

const app = new App({
  target: document.getElementById('app'),
})

export default app
