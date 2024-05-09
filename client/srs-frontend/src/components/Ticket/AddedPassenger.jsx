import axios from 'axios'
import React from 'react'
import { URL } from '../../data/constants'
import { toast } from 'react-toastify';

const AddedPassenger = ({ allPassengerDetails, scheduleId, changeShowPassengerTable }) => {
    console.log("Val of scheduleId", scheduleId);
    const handleReservation = async () => {
        const res = await axios.post(URL + "/user/reserveticket", {
            reservationBean: {
                scheduleId: scheduleId
            },
            passengerBeanLists: allPassengerDetails
        });
        toast.info(res.data, {
            position: "top-center",
            autoClose: 5000,
            hideProgressBar: false,
            closeOnClick: true,
            pauseOnHover: true,
            draggable: true,
            progress: undefined,
            theme: "colored",
        });
        changeShowPassengerTable(false);
        // console.log("Val of res is:", res);
    }
    return (
        <div className='flex justify-center items-center w-96'>
            <table className='rounded-2xl shadow-2xl font-[Poppins] border-2 mt-6 border-cyan-200 w-10/12'>
                <thead className='text-white'>
                    <tr>
                        {/* <th className='py-3 bg-cyan-800'>S.No</th> */}
                        <th className='py-3 bg-cyan-800'>Passenger Name</th>
                        <th className='py-3 bg-cyan-800'>Gender</th>
                        <th className='py-3 bg-cyan-800'>Age</th>
                    </tr>
                </thead>
                <tbody className='text-cyan-900 text-center'>
                    {allPassengerDetails.map((passenger) => {
                        return <tr className={'bg-cyan-400 hover:bg-cyan-100 cursor-pointer duration-300'}>
                            <th className='py-3 px-6'>{passenger.name}</th>
                            <th className='py-3 px-6'>{passenger.gender}</th>
                            <th className='py-3 px-6'>{passenger.age}</th>
                        </tr>
                    })}
                </tbody>
            </table>
            <button class="bg-blue-500 hover:bg-blue-700 ml-2   text-white font-bold py-2 px-4 border border-blue-700 rounded" onClick={handleReservation}>
                Reserve Ticket
            </button>
        </div>
    )
}

export default AddedPassenger