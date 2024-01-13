import React from 'react'
import { Advertise, Banner, Explore, Footer, Hero, Memory, Navbar, Newslatter } from './components';
import { bannerAPI, brands, footerAPI, hero, memory, navlinks, placesAPI } from './data/shipifydata';

const Home = () => {
    console.log("Inside home");
    return (
        <>
            {/* <Navbar navlinks={navlinks} /> */}
            <Hero hero={hero} />
            <Memory memory={memory} />
            <Explore title="Discover the enchantment of sailing to this stunning destination." placesAPI={placesAPI} />
            {/* <Advertise brands={brands} /> */}
            {/* <Pricings pricingapi={pricingapi} /> */}
            <Banner bannerAPI={bannerAPI} />
            <Newslatter />
            <Footer footerAPI={footerAPI} />
        </>
    )
}

export default Home