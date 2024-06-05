import '../styles/Seller.css'
import logo from '../assets/logo-react.png'
import search from '../assets/search-icon.png'
import profile from '../assets/profile-pic.jpg'
function Seller(){
    return(
       <div className='navbar'>
       <img src={logo} alt=""  className='logo'/>
       <ul>
       <li> <a href="">Home</a> </li>
       <li> <a href="">Request</a> </li>
       <li> <a href="">Add New</a> </li>
       <li> <a href="">About</a> </li>
       </ul>
       <div className='search-box'>
        <input type="text" placeholder="search"/>
        <img src={search} alt="" />
       </div>
       <div className='Profile'>
        <img src={profile} alt="profile" className='profile-pic' />
        <p className='seller-name'>Stitch</p>
       </div>
      
       </div>
    )
}

export default Seller