# GrapeTree Application Portal

The GrapeTree Application Portal is a chatbot-powered platform designed to streamline the applicant intake and screening process. Deployed on WordPress and powered by the Teneo.ai SDK, this system integrates deeply with various APIs and backend systems to enhance recruitment efforts and provide a seamless onboarding experience. Applicants are greeted with a conversation chatbot that can recognize variable input and react to user sentament. As the user enters information, the chatbot records key data types like Name, Phone Number, Email as well as controlled questiosn such as license type, years of experience and location data.

## Key Features

- **Applicant Data Intake & Screening**: Automatically collects and screens candidate information before integrating into the Applicant Tracking System (ATS).
- **Marketing Campaign Reporting**: Tracks site referral data and reports back to the ATS for marketing campaign analysis.
- **Task Creation for Onboarding**: Generates onboarding tasks in the internal task manager, ensuring smooth candidate transitions.
- **Conversational AI**: Detects user sentiment and adjusts responses accordingly to improve engagement.
- **SafetyNet Feature**: Attempts to rescue failing chats, while capturing key user data (e.g., name, phone, email) for future use.
- **API Integrations**: Contains 17 API integrations, streamlining processes such as data collection, task creation, and ATS updates.

## Technologies Used

- **JavaScript**: Handles client-side logic and interactions within the chatbot and WordPress environment.
- **Groovy Script**: Scripting for backend processes and API integration logic.
- **CSS**: Manages the chatbot's styling and appearance on the WordPress site.
- **Teneo.AI SDK**: Provides the core functionality for building and managing conversational AI experiences.
- **Apache HTTP**: Manages server communication and API requests.


## API Integrations

The application integrates with 17 different APIs to:
- Screen applicants and store data.
- Report site referral and campaign data.
- Create onboarding tasks.
- Provide real-time updates to the ATS.

## Contributing

Merging and Contributing is limited

## Deployed Site

https://apply.grapetree.com/

## License

This project is licensed under the MIT License. See the `LICENSE` file for more details.
