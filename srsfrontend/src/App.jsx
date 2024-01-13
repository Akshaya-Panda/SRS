import React, { createContext, useState } from 'react';
import { Navbar, Hero, Footer, Memory, Explore, Advertise, Pricings, Banner, Newslatter } from './components';
import { hero, navlinks, memory, placesAPI, brands, pricingapi, bannerAPI, footerAPI } from './data/shipifydata';
import Home from './Home.jsx';
import { Route, Routes } from 'react-router-dom';
import Login from './components/Login/Login.jsx';
import Register from './components/Register/Register.jsx';
import { ToastContainer } from 'react-toastify';
import ScheduleByRoute from './components/Schedule/ScheduleByRoute.jsx';
import ReserveTicket from './components/Ticket/ReserveTicket.jsx';
import TicketView from './components/Ticket/TicketView.jsx';

export const LoginContext = createContext();
const App = () => {
  const [loginStatus, setLoginStatus] = useState(0);
  const [uId, setUid] = useState();
  return (
    <>
      <LoginContext.Provider value={{ loginStatus, setLoginStatus, uId, setUid }}>
        <Navbar navlinks={navlinks} />
        {/* <Hero hero={hero} /> */}
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route path="/schedulebyroute" element={<ScheduleByRoute />} />
          <Route path="/reserveticket" element={<ReserveTicket />} />
          <Route path="/viewticket" element={<TicketView/>}/>
        </Routes>
      </LoginContext.Provider>
      <ToastContainer />
    </>
  );
};

export default App;