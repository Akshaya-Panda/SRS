
export const adminAuthorize = (userId, navigate) => {
    switch (userId) {
        case "AD-001":
            addFlight(cb);
            break;
        case "AD-002":
            deleteFlight(cb);
            break;
        case "AD-003":
            viewFlight(cb);
            break;
        case "AD-004":
            editFlight(cb);
            break;
        case "AD-005":
            addRoute(cb);
            break;
        case "AD-006":
            deleteRoute(cb);
            break;
        case "AD-007":
            viewRoute(cb);
            break;
        case "AD-008":
            editRoute(cb);
            break;
        case "AD-009":
            addSchedule(cb);
            break;
        case "AD-010":
            deleteSchedule(cb);
            break;
        case "AD-011":
            viewSchedule(cb);
            break;
        case "AD-012":
            editSchedule(cb);
            break;
        case "AD-013":
            viewPassenger(cb);
            break;
        default:
            break;
    }
}

export const customerAuthorize = (userId, navigate) => {
    switch (userId) {
        case "US-001":
            // registerUser();
            navigate("/register");
            break;
        case "US-002":
            // viewSchedule(cb);
            navigate("/schedulebyroute");
            break;
        case "US-003":
            // reserveTickets(cb);
            navigate("/reserveticket");
            break;
        case "US-004":
            // viewTickets(cb);
            navigate("/viewticket");
            break;
        case "US-005":
            cancelTickets(cb);
            break;
        default:
            break;
    }

}