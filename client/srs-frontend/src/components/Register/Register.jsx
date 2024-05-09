import axios from 'axios';
import React, { useState } from 'react'
import { URL } from '../../data/constants';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

const Register = () => {
    const [userDetails, setUserDetails] = useState({
        firstName: "",
        lastName: "",
        dateOfBirth: "",
        gender: "",
        street: "",
        location: "",
        city: "",
        state: "",
        pincode: "",
        mobileNo: "",
        emailID: ""
    });
    const handleChange = (e) => {
        setUserDetails({ ...userDetails, [e.target.name]: e.target.value });
    }
    const handleSubmit = async (e) => {
        console.log("Val of userDetails is:", userDetails);
        const res = await axios.post(URL + "/credentials/registration", userDetails);
        console.log("val of res is", res);
        if (res.data == 1) {
            setUserDetails({
                firstName: "",
                lastName: "",
                dateOfBirth: "",
                gender: "",
                street: "",
                location: "",
                city: "",
                state: "",
                pincode: "",
                mobileNo: "",
                emailID: ""
            });
            toast.success('😍 Wow!! Registration Successful', {
                position: "top-center",
                autoClose: 3000,
                hideProgressBar: false,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
                theme: "colored",
            });
        } else {
            toast.error('Sorry!! Something went wrong..', {
                position: "top-center",
                autoClose: 5000,
                hideProgressBar: false,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
                theme: "colored",
            });
        }

        console.log("Val o")
    }
    return (
        <div className='flex flex-col bg-gradient-to-b from-emerald-200 to-white h-auto w-auto '>
            <div className='travigo-container grid items-start justify-items-center'>
                <div className='my-20 ' >
                    <div className='border'>                    
                    <section className="h-screen flex flex-row md:flex-row justify-center  space-x-10 md:space-y-0 md:space-x-16 items-center  mx-5 md:mx-0 md:my-0">
                        <div className="md:w-1/3 max-w-sm">
                            <input className="text-sm w-full px-4 py-2 border border-solid border-gray-300 rounded" type="text" name="firstName" placeholder="First Name" value={userDetails.firstName} onChange={(e) => handleChange(e)} />
                            <input className="text-sm w-full px-4 py-2 border border-solid border-gray-300 rounded mt-4" type="text" name="lastName" value={userDetails.lastName} placeholder="Last Name" onChange={(e) => handleChange(e)} />
                            <input className="text-sm w-full px-4 py-2 border border-solid border-gray-300 rounded mt-4" type="text" value={userDetails.dateOfBirth} name="dateOfBirth" placeholder="Date of Birth" onChange={(e) => handleChange(e)} />
                            <input className="text-sm w-full px-4 py-2 border border-solid border-gray-300 rounded mt-4" type="text" value={userDetails.gender} name="gender" placeholder="Gender" onChange={(e) => handleChange(e)} />
                            <input className="text-sm w-full px-4 py-2 border border-solid border-gray-300 rounded mt-4" type="text" value={userDetails.street} name="street" placeholder="Street" onChange={(e) => handleChange(e)} />
                            <input className="text-sm w-full px-4 py-2 border border-solid border-gray-300 rounded mt-4" type="text" name="location" value={userDetails.location} placeholder="Location" onChange={(e) => handleChange(e)} />

                        </div>
                        <div className="md:w-1/3 max-w-sm ">
                            <input className="text-sm w-full px-4 py-2 border border-solid border-gray-300 rounded" type="text" name="city" value={userDetails.city} placeholder="City" onChange={(e) => handleChange(e)} />
                            <input className="text-sm w-full px-4 py-2 border border-solid border-gray-300 rounded mt-4" type="text" name="state" value={userDetails.state} placeholder="State" onChange={(e) => handleChange(e)} />
                            <input className="text-sm w-full px-4 py-2 border border-solid border-gray-300 rounded mt-4" type="text" name="pincode" value={userDetails.pincode} placeholder="Pincode" onChange={(e) => handleChange(e)} />
                            <input className="text-sm w-full px-4 py-2 border border-solid border-gray-300 rounded mt-4" type="text" name="mobileNo" value={userDetails.mobileNo} placeholder="Mobile Number" onChange={(e) => handleChange(e)} />
                            <input className="text-sm w-full px-4 py-2 border border-solid border-gray-300 rounded mt-4" type="text" name="emailID" value={userDetails.emailID} placeholder="Email Address" onChange={(e) => handleChange(e)} />

                            <div className="text-center md:text-left">
                                <button className="mt-4 bg-blue-600 hover:bg-blue-700 px-4 py-2 text-white uppercase rounded text-xs tracking-wider" type="submit" onClick={(event) => handleSubmit(event)}>Registration</button>
                            </div>
                            {/* <div className="mt-4 font-semibold text-sm text-slate-500 text-center md:text-left">
                                You are going to be a part of <a className="text-red-600 hover:underline hover:underline-offset-4" >Excellence!!</a>
                            </div> */}
                        </div>
                    </section>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Register