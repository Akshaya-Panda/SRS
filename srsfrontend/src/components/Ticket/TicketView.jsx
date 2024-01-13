import axios from 'axios'
import React, { useEffect, useState } from 'react'
import { URL } from '../../data/constants'
import { BeatLoader, ClockLoader, PacmanLoader } from 'react-spinners';

const TicketView = () => {
    const [reservationId, setReservationId] = useState();
    const [allReservation, setAllReservation] = useState([]);
    const [selectedReservation, setSelectedReservation] = useState();
    const [allPassengers, setAllPassengers] = useState();


    const viewTicket = async () => {
        console.log("val of reservationID", reservationId);
        const res1 = await axios.get(URL + "/user/viewpassenger/" + reservationId);
        const res2 = await axios.get(URL + "/user/viewreservation/" + reservationId);

        setAllPassengers()
        console.log("Val of res.daa is:", res1.data);
        console.log("Val of res", res2.data);
    }
    const handleChangeTicket = (e) => {
        setReservationId(e.target.value);
        console.log("val of e is", e.target.value);
    }
    useEffect(() => {
        (async function temp() {
            const res = await axios.get(URL + "/user/getallreservation");
            console.log("Val of res is:", res.data);
            setAllReservation([...res.data]);
        })()
    })
    return (
        < div className='flex flex-col bg-gradient-to-b from-emerald-200 to-white h-auto w-auto ' >
            <div className='travigo-container grid items-start justify-items-center'>
                <div className='my-20 flex justify-between ' >
                    <span className='font-extrabold mr-3 mt-2'>Reservation ID</span>
                    {allReservation.length > 0 ? <select className='w-32 h-10' value={selectedReservation} onChange={handleChangeTicket}>
                        {
                            allReservation.map((r, i) => {
                                return <option value={r.reservationId} key={i}>{r.reservationId}</option>
                            })
                        }
                    </select> : <BeatLoader color="#36d7b7" />}
                    {/* <input className="text-sm w-full px-4 py-2 mt-4 border border-solid border-gray-300 rounded" type="text" name="reservationId" placeholder="Reservation ID" value={reservationId} onChange={(e) => setReservationId(e.target.value)} /> */}
                    <button type="button" class="py-2.5 px-5 ml-3 me-2  mb-2 text-sm font-medium text-black-700 focus:outline-none bg-white rounded-lg border border-gray-200 hover:bg-gray-100 hover:text-blue-700 focus:z-10 focus:ring-4 focus:ring-gray-200 dark:focus:ring-gray-700 dark:bg-gray-800 dark:text-gray-400 dark:border-gray-600 dark:hover:text-white dark:hover:bg-gray-700" onClick={viewTicket}>View Ticket</button>
                </div>
                <ClockLoader color="#36d7b7" />
            </div>
        </div>
    )
}

export default TicketView