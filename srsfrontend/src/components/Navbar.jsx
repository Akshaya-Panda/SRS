import React, { useContext, useEffect, useState } from 'react'
import { NavLink, useNavigate } from 'react-router-dom'
import logo from '../images/logo.png';
import menu from '../images/menu.svg';
import PopupMenu from './PopupMenu';
import { LoginContext } from '../App';
import axios from 'axios';
import { URL } from '../data/constants';
const Navbar = ({ navlinks }) => {
  const [popupState, setPopupState] = useState(false);
  const [navState, setNavState] = useState(false);
  const onTriggerPopup = () => setPopupState(!popupState);
  const { loginStatus, setLoginStatus, uId, setUid } = useContext(LoginContext);
  const navigate = useNavigate();
  const onNavScroll = () => {
    if (window.scrollY > 180) {
      setNavState(true);
    } else {
      setNavState(false);
    }
  }

  useEffect(() => {
    window.addEventListener('scroll', onNavScroll);

    return () => {
      window.removeEventListener('scroll', onNavScroll);
    }
  }, [])

  const changeLoginStatus = async () => {
    const res = await axios.get(URL + "/credentials/logout/" + uId);
    console.log("Val of logout res is:", res.data);
    setUid("");
    setLoginStatus(0);
    navigate("/login");
  }
  // console.log(popupState)

  return (
    <>
      <header className={`
        nav-default ${navState && 'nav-sticky'}
      `}>
        <nav className='flex items-center justify-between travigo-container'>
          <NavLink to={`/`} className="flex items-center">
            {/* <img src={logo} alt='logo/img' className='w-22 h-9 object-fill' /> */}
            <h1 className='font-bold text-4xl w-22 h-9 object-fill'>Shipify</h1>
          </NavLink>
          {/* <ul className='flex items-center lg:hidden gap-7'>
            {navlinks?.map((val, i) => (
              <li key={i}><NavLink to={'#'} className="text-lg text-slate-900">{val.link}</NavLink></li>
            ))}
          </ul> */}
          {loginStatus == 0 ? <ul className='flex items-center lg:hidden'>
            <li><button type='button' className='button-emrald px-7 text-base' >Photos</button></li>
          </ul> : <ul className='flex items-center lg:hidden'>
            <li><button type='button' className='button-emrald px-7 text-base' onClick={changeLoginStatus}>Logout</button></li>
          </ul>}
          <ul className='hidden lg:flex items-center'>
            <li><button type='button' className='flex items-center justify-center transition-all duration-200 active:scale-90 cursor-pointer' onClick={onTriggerPopup}><img src={menu} alt="menu/svg" className='object-cover shadow-sm filter' /></button></li>
          </ul>
        </nav>
      </header>
      <PopupMenu navlinks={navlinks} popupState={popupState} />
    </>
  )
}

export default Navbar