import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api';

const api = axios.create({
    baseURL: API_BASE_URL,
    headers: {
        'Content-Type': 'application/json',
    },
});

export const driverService = {
    getAvailableDrivers: async () => {
        const response = await api.get('/drivers/available');
        return response.data;
    },
    getAllDrivers: async () => {
        const response = await api.get('/drivers');
        return response.data;
    }
};

export const bookingService = {
    createBooking: async (bookingRequest) => {
        const response = await api.post('/bookings', bookingRequest);
        return response.data;
    },
    getBookingById: async (id) => {
        const response = await api.get(`/bookings/${id}`);
        return response.data;
    }
};

export default api;
