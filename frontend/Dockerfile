# Use an official Nginx runtime as a parent image
FROM nginx:alpine

# Set the working directory to /app
WORKDIR /app

# Copy the built React app files into the image
COPY build/ /app/

# Remove the default Nginx configuration file
RUN rm /etc/nginx/conf.d/default.conf

# Copy your custom Nginx configuration file
COPY nginx.conf /etc/nginx/conf.d/

# Expose port 80 to the outside world
EXPOSE 80

# Start Nginx when the container starts
CMD ["nginx", "-g", "daemon off;"]
