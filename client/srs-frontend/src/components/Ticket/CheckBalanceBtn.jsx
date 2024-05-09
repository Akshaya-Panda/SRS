import React from 'react'
import { URL } from '../../data/constants'
import { toast } from 'react-toastify'
import axios from 'axios'

const CheckBalanceBtn = () => {
    const showBalance = async () => {
        const res = await axios.get(URL + "/user/getbalance")
        console.log("val of balance is:", res.data);
        const balance = 'Your account Balance is ' + res.data + "/-";
        toast.info(balance, {
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
    return (
        <div>
            <button type="button" class="py-2.5 px-5 me-2 mb-2 text-sm font-medium text-black-700 focus:outline-none bg-white rounded-lg border border-gray-200 hover:bg-gray-100 hover:text-blue-700 focus:z-10 focus:ring-4 focus:ring-gray-200 dark:focus:ring-gray-700 dark:bg-gray-800 dark:text-gray-400 dark:border-gray-600 dark:hover:text-white dark:hover:bg-gray-700" onClick={showBalance}>Check Balance</button>
        </div>
    )
}

export default CheckBalanceBtn