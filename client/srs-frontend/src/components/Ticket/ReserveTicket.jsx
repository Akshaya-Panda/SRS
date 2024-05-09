import React, { useEffect, useState } from 'react'
import { URL } from '../../data/constants';
import { BeatLoader, PacmanLoader } from 'react-spinners';
import axios from 'axios';
import AddedPassenger from './AddedPassenger';
import CheckBalanceBtn from './CheckBalanceBtn';

const ReserveTicket = () => {
    const [schedules, setSchedules] = useState([]);
    const [selectedScheduleIdx, setSelectedScheduleIdx] = useState();
    const [selectedSchedule, setSelectedSchedule] = useState({});
    const [toggleAddPassengerForm, setToggleAddPassengerForm] = useState(false);
    const [passengerDetails, setPassengerDetails] = useState({
        name: "", age: "", gender: ""
    });//single passenger
    const [allPassengerDetails, setAllPassengerDetails] = useState([]);//all passenger
    const [showPassengerTable, setShowPassengerTable] = useState(false);

    const handleScheduleChange = (e) => {
        console.log("val of e.target.vale", e.target.value);
        setSelectedScheduleIdx(e.target.value);
        setSelectedSchedule(schedules[e.target.value]);
    }
    const handlePassengerChange = (e) => {
        setPassengerDetails({ ...passengerDetails, [e.target.name]: e.target.value });
    }
    const handleAddPassengerDetails = (e) => {
        setAllPassengerDetails([...allPassengerDetails, { ...passengerDetails }]);
        setPassengerDetails({
            name: "", age: "", gender: ""
        })
        setShowPassengerTable(true);

    }
    const changeShowPassengerTable = (val) => {
        // console.log("val of val", val);
        setShowPassengerTable(false);
        setPassengerDetails({
            name: "", age: "", gender: ""
        })
    }
    console.log("Val of selected schedule is:", Object.keys(selectedSchedule).length);
    console.log("val of selected scheudle is:", selectedSchedule);
    console.log("Valof passenger details: ", passengerDetails);
    console.log("Valof all passenger details: ", allPassengerDetails);
    useEffect(() => {
        (async function getScheduleData() {
            const res = await axios.get(URL + "/user/getallschedules");
            console.log("Val of res data is:", res.data);
            setSchedules([...res.data]);
            // res.data.length > 0 ? setSelectedSchedule(res.data[0]) : "";
        })();
    }, [])
    console.log("vall of all Schedules ", schedules);
    return (
        <div className='flex flex-col bg-gradient-to-b from-emerald-200 to-white h-auto w-auto '>
            <div className='travigo-container grid items-start '>
                <div className='mt-28 ' >
                    <div className='flex justify-between'>
                        <label >
                            <span className='font-extrabold'>Schedule</span>
                            {schedules.length > 0 ? <select className="mx-4" value={selectedScheduleIdx} onChange={handleScheduleChange}>

                                {schedules.map((schedule, idx) => {
                                    // { console.log(selectedSchedule == schedule) }
                                    return <option value={idx} key={idx} >{idx + 1}</option>
                                })}
                            </select> : <span className='mx-4 pt-6'><BeatLoader color="#36d7b7" /></span>}
                        </label>
                        <button type="button" class="py-2.5 px-5 me-2  mb-2 text-sm font-medium text-black-700 focus:outline-none bg-white rounded-lg border border-gray-200 hover:bg-gray-100 hover:text-blue-700 focus:z-10 focus:ring-4 focus:ring-gray-200 dark:focus:ring-gray-700 dark:bg-gray-800 dark:text-gray-400 dark:border-gray-600 dark:hover:text-white dark:hover:bg-gray-700" onClick={() => setToggleAddPassengerForm(!toggleAddPassengerForm)}>Add Passenger</button>
                        <CheckBalanceBtn/>
                        {/* <label >
                            <span className='font-extrabold'>Source</span>
                            {sources.length > 0 ? <select className="mx-4" value={selectedSource} onChange={handleSourceChange}>

                                {sources.map(source => {
                                    { console.log(selectedSource == source) }
                                    return <option value={source} >{source}</option>
                                })}
                            </select> : <span className='mx-4 pt-6'><BeatLoader color="#36d7b7" /></span>}
                        </label> */}
                    </div>
                    <div className='flex justify-center'>
                        
                        {toggleAddPassengerForm ?
                            <div className='w-80'>
                                <div >
                                    <input className="text-sm w-full px-4 py-2 mt-4 border border-solid border-gray-300 rounded" type="text" name="name" placeholder="User Name" value={passengerDetails.name} onChange={(e) => handlePassengerChange(e)} />
                                    {/* <br/> */}
                                    <input className="text-sm w-full px-4 py-2 mt-2 border border-solid border-gray-300 rounded" type="text" name="age" placeholder="Age" value={passengerDetails.age} onChange={(e) => handlePassengerChange(e)} />

                                    <input className="text-sm w-full px-4 py-2 mt-2 border border-solid border-gray-300 rounded" type="text" name="gender" placeholder="Gender" value={passengerDetails.gender} onChange={(e) => handlePassengerChange(e)} />
                                </div>

                                <a href="#_" class="relative inline-flex items-center justify-center p-4 px-6 py-3 mt-4 overflow-hidden font-medium text-green-600 transition duration-300 ease-out border-2 border-green-500 rounded-full shadow-md group">
                                    <span class="absolute inset-0 flex items-center justify-center w-full h-full text-white duration-300 -translate-x-full bg-green-500 group-hover:translate-x-0 ease" onClick={handleAddPassengerDetails}>
                                        <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M14 5l7 7m0 0l-7 7m7-7H3"></path></svg>
                                    </span>
                                    <span class="absolute flex items-center justify-center w-full h-full text-green-500 transition-all duration-300 transform group-hover:translate-x-full ease" onClick={handleAddPassengerDetails}>Add</span>
                                    <span class="relative invisible" onClick={handleAddPassengerDetails}>Add</span>
                                </a>
                                {showPassengerTable ? <AddedPassenger allPassengerDetails={allPassengerDetails} scheduleId={selectedSchedule.scheduleId} changeShowPassengerTable={changeShowPassengerTable} /> : ""}
                            </div>
                            :
                            Object.keys(selectedSchedule).length > 0 ? (<table className='rounded-2xl shadow-2xl font-[Poppins] border-2 mt-6 border-cyan-200 w-11/12'>
                                <thead className='text-white'>
                                    <tr>
                                        {/* <th className='py-3 bg-cyan-800'>S.No</th> */}
                                        <th className='py-3 bg-cyan-800'>Ship Name</th>
                                        <th className='py-3 bg-cyan-800'>Source</th>
                                        <th className='py-3 bg-cyan-800'>Destination</th>
                                        <th className='py-3 bg-cyan-800'>Travel Duration</th>
                                        <th className='py-3 bg-cyan-800'>Available Days</th>
                                        <th className='py-3 bg-cyan-800'>Departure Time</th>
                                        <th className='py-3 bg-cyan-800'>Journey Date</th>
                                        <th className='py-3 bg-cyan-800'>Fare</th>
                                    </tr>
                                </thead>
                                <tbody className='text-cyan-900 text-center'>
                                    <tr className={'bg-cyan-400 hover:bg-cyan-100 cursor-pointer duration-300'}>
                                        {/* <th className='py-3 px-6'>{idx + 1}</th> */}
                                        <th className='py-3 px-6'>{selectedSchedule.shipBean.shipname}</th>
                                        <th className='py-3 px-6'>{selectedSchedule.routeBean.source}</th>
                                        <th className='py-3 px-6'>{selectedSchedule.routeBean.destination}</th>
                                        <th className='py-3 px-6'>{selectedSchedule.routeBean.travelDuration}</th>
                                        {/* <th className='py-3 px-6'>{selectedSchedule.shipBean.seatingcapacity}</th> */}
                                        <th className='py-3 px-6'>{selectedSchedule.availableDays}</th>
                                        <th className='py-3 px-6'>{selectedSchedule.departureTime}</th>
                                        <th className='py-3 px-6'>{selectedSchedule.startDate}</th>
                                        <th className='py-3 px-6'>{selectedSchedule.routeBean.fare}/-</th>
                                    </tr>
                                </tbody>
                            </table>) : <div className='mt-8'><PacmanLoader color="#36d7b7" /></div>
                        }
                    
                    </div>
                </div>
            </div>
        </div>
    )
}

export default ReserveTicket