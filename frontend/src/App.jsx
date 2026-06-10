import { useState } from 'react';
import DriverList from './components/DriverList';
import BookingForm from './components/BookingForm';
import BookingConfirmation from './components/BookingConfirmation';
import './index.css';

function App() {
    const [bookingResponse, setBookingResponse] = useState(null);

    const handleBookingSuccess = (response) => {
        setBookingResponse(response);
    };

    const handleResetBooking = () => {
        setBookingResponse(null);
    };

    return (
        <div className="app-container">
            <header>
                <h1>NextGen Ride</h1>
                <p>Premium ride booking at your fingertips.</p>
            </header>

            <main>
                {!bookingResponse ? (
                    <div className="main-grid">
                        <BookingForm onBookingSuccess={handleBookingSuccess} />
                        <DriverList />
                    </div>
                ) : (
                    <div style={{ maxWidth: '600px', margin: '0 auto' }}>
                        <BookingConfirmation 
                            booking={bookingResponse} 
                            onReset={handleResetBooking} 
                        />
                    </div>
                )}
            </main>
        </div>
    );
}

export default App;
