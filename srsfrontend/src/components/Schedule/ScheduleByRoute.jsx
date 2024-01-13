import React, { useEffect, useState, CSSProperties } from 'react'
import { URL } from '../../data/constants';
import axios from 'axios';
// import ReactDatePicker from 'react-datepicker';
import ReactDatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import ClipLoader from "react-spinners/ClipLoader";
import { BeatLoader, BounceLoader } from 'react-spinners';

const override = {
    display: "block",
    margin: "0 auto",
    borderColor: "red",
};

const ScheduleByRoute = () => {
    const [value, setValue] = useState('fruit');
    const handleChange = (event) => {
        setValue(event.target.value);
    };
    const [sources, setSources] = useState([]);
    const [destinations, setDestinations] = useState([]);
    const [selectedSource, setSelectedSource] = useState();
    const [selectedDestination, setSelectedDestination] = useState();
    const [selectedDate, setSelectedDate] = useState(new Date());
    const [allSchedulesData, setAllSchedulesData] = useState([]);

    const handleDateChange = (date) => {
        setSelectedDate(date);
    };

    const formatDate = (date) => {
        if (!date) return '';

        const day = date.getDate().toString().padStart(2, '0');
        const month = (date.getMonth() + 1).toString().padStart(2, '0');
        const year = date.getFullYear();

        return `${day}/${month}/${year}`;
    };
    const handleSourceChange = (e) => {
        console.log("vAl of ", e.target.value);
        setSelectedSource(e.target.value);
    }
    const handleDestinationChange = (e) => {
        console.log("vAl of ", e.target.value);
        setSelectedDestination(e.target.value);
    }
    const getScheduleByRoute = async () => {
        const dateInString = formatDate(selectedDate);
        //get the data from backend
        const res = await axios.post(URL + "/user/viewschedulebyroute", {
            source: selectedSource,
            destination: selectedDestination,
            searchDate: dateInString,
        })
        console.log("Val of res is :", res.data);
        setAllSchedulesData([...res.data]);
    }
    useEffect(() => {
        (async function getData() {
            //get all the source and destination
            const res = await axios.get(URL + "/user/getallsourcedestination");
            console.log("Val of res is:", res.data);
            setSources([...res.data.sources]);
            setDestinations([...res.data.destination]);
            //get all the available journeydate
        })()
    }, []);
    return (
        <>
            <div className='flex flex-col bg-gradient-to-b from-emerald-200 to-white h-auto w-auto '>
                <div className='travigo-container grid items-start '>
                    <div className='mt-28 ' >
                        <div className='flex justify-between'>
                            <label >
                                <span className='font-extrabold'>Source</span>
                                {sources.length > 0 ? <select className="mx-4" value={selectedSource} onChange={handleSourceChange}>

                                    {sources.map(source => {
                                        { console.log(selectedSource == source) }
                                        return <option value={source} >{source}</option>
                                    })}
                                </select> : <span className='mx-4 pt-6'><BeatLoader color="#36d7b7" /></span>}
                            </label>
                            <label>
                                <span className='font-extrabold'>Destination</span>
                                {destinations.length > 0 ? <select className='mx-4' value={selectedDestination} onChange={handleDestinationChange}>

                                    {destinations.map(destination => {
                                        return <option value={destination} >{destination}</option>
                                    })}
                                </select> : <span className='mx-4'><BeatLoader color="#36d7b7" /></span>}
                            </label>
                            <label>
                                <span className='font-extrabold'>Journey Date</span>
                                <ReactDatePicker
                                    className='mx-4'
                                    selected={selectedDate}
                                    onChange={handleDateChange}
                                    dateFormat="dd/MM/yyyy"
                                />
                            </label>
                            <button id="facebook" class="bg-cyan-200  sticky duration-500 border-2 border-blue-600 fixed  w-12 transform hover:-translate-y-3   h-12 text-2xl rounded-full hover:bg-blue-600 hover:text-white text-blue-600 " onClick={getScheduleByRoute}>
                                🚢
                            </button>
                        </div>
                        {/* <table className='bg-cyan-900 flex justify-center items=center h-screen'> */}
                        <div className='flex justify-center items-center mt-20'>
                            <table className='rounded-2xl shadow-2xl font-[Poppins] border-2 border-cyan-200 w-10/12'>
                                <thead className='text-white'>
                                    <tr>
                                        <th className='py-3 bg-cyan-800'>S.No</th>
                                        <th className='py-3 bg-cyan-800'>Ship Name</th>
                                        <th className='py-3 bg-cyan-800'>Reservation Cap.</th>
                                        <th className='py-3 bg-cyan-800'>Seating Cap.</th>
                                        <th className='py-3 bg-cyan-800'>Departure Time</th>
                                        <th className='py-3 bg-cyan-800'>Available Days</th>
                                        <th className='py-3 bg-cyan-800'>Fare</th>
                                        <th className='py-3 bg-cyan-800'>Travel Duration</th>
                                    </tr>
                                </thead>
                                <tbody className='text-cyan-900 text-center'>
                                    {allSchedulesData.map((schedule, idx) => {
                                        const colorName = "bg-cyan-" + ((idx + 1) * 200)
                                        console.log("coloname is",colorName);
                                        return <tr className={colorName + ' hover:bg-cyan-100 cursor-pointer duration-300'}>
                                            <th className='py-3 px-6'>{idx + 1}</th>
                                            <th className='py-3 px-6'>{schedule.shipBean.shipname}</th>
                                            <th className='py-3 px-6'>{schedule.shipBean.reservationcapacity}</th>
                                            <th className='py-3 px-6'>{schedule.shipBean.seatingcapacity}</th>
                                            <th className='py-3 px-6'>{schedule.departureTime}</th>
                                            <th className='py-3 px-6'>{schedule.availableDays}</th>
                                            <th className='py-3 px-6'>{schedule.routeBean.fare}</th>
                                            <th className='py-3 px-6'>{schedule.routeBean.travelDuration}</th>
                                        </tr>
                                    })}
                                </tbody>
                            </table>
                            {/* <table className='rounded-2xl shadow-2xl font-[Poppins] border-2 border-cyan-200 w-6/12'>
                                <thead className='text-white'>
                                    <tr>
                                        <th className='py-3 bg-cyan-800'>S.No</th>
                                        <th className='py-3 bg-cyan-800'>S.No</th>
                                        <th className='py-3 bg-cyan-800'>S.No</th>
                                        <th className='py-3 bg-cyan-800'>S.No</th>
                                    </tr>
                                </thead>
                                <tbody className='text-cyan-900 text-center'>
                                    <tr className='bg-cyan-200 hover:bg-cyan-100 cursor-pointer duration-300'>
                                        <th className='py-3 px-6'>val</th>
                                        <th className='py-3 px-6'>val</th>
                                        <th className='py-3 px-6'>val</th>
                                        <th className='py-3 px-6'>val</th>
                                    </tr>
                                    <tr className='bg-cyan-300 hover:bg-cyan-100 cursor-pointer duration-300'>
                                        <th className='py-3 px-6'>val</th>
                                        <th className='py-3 px-6'>val</th>
                                        <th className='py-3 px-6'>val</th>
                                        <th className='py-3 px-6'>val</th>
                                    </tr>
                                    <tr className='bg-cyan-500 hover:bg-cyan-100 cursor-pointer duration-300'>
                                        <th className='py-3 px-6'>val</th>
                                        <th className='py-3 px-6'>val</th>
                                        <th className='py-3 px-6'>val</th>
                                        <th className='py-3 px-6'>val</th>
                                    </tr>
                                </tbody>
                            </table> */}
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}

export default ScheduleByRoute