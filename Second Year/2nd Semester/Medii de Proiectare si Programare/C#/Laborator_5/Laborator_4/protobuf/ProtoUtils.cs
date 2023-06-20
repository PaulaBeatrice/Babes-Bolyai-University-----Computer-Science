using System;
using System.Collections.Generic;
using System.Dynamic;
using Triathlon.Protocol;
using proto=Triathlon.Protocol;
using Referee = model.Referee;
using Result = model.Result;

namespace protobuf
{
    static class ProtoUtils
    {
        public static TriathlonRequest createLoginRequest(model.Referee referee)
        {
            proto.Referee theReferee = new proto.Referee
            {
                Id = referee.Id,
                Username = referee.Username,
                Passwrod = referee.Password,
                FirstName = referee.First_Name,
                LastName = referee.Last_Name,
                Activity = referee.Activity
            };
            TriathlonRequest request = new TriathlonRequest
            {
                Type = TriathlonRequest.Types.Type.Login,
                Referee = theReferee
            };
            return request;
        }
        
        public static TriathlonRequest createLogoutRequest(model.Referee referee)
        {
            proto.Referee theReferee =  new proto.Referee
            {
                Id = referee.Id,
                Username = referee.Username,
                Passwrod = referee.Password,
                FirstName = referee.First_Name,
                LastName = referee.Last_Name,
                Activity = referee.Activity
            };
            TriathlonRequest request = new TriathlonRequest
            {
                Type = TriathlonRequest.Types.Type.Logout,
                Referee = theReferee
            };
            return request;
        }

        public static TriathlonRequest createAddScoreRequest(model.Result result)
        {
            proto.Result theResult = new proto.Result
            {
                Activity = result.Activity,
                Id = result.Id,
                ParticipantId = result.Participant.Id,
                Points = result.Points,
                RefereeId = result.Referee.Id
            };
            TriathlonRequest request = new TriathlonRequest
            {
                Type = TriathlonRequest.Types.Type.AddScore,
                Result = theResult
            };
            return request;
        }

        public static TriathlonRequest createGetTopPartRequest()
        {
            TriathlonRequest request = new TriathlonRequest
            {
                Type = TriathlonRequest.Types.Type.GetTop,
            };
            return request;
        }

        public static TriathlonRequest createGetNotedRequest(model.Referee referee)
        {
            proto.Referee theReferee = new proto.Referee
            {
                Id = referee.Id,
                Username = referee.Username,
                Passwrod = referee.Password,
                FirstName = referee.First_Name,
                LastName = referee.Last_Name,
                Activity = referee.Activity
            };
            TriathlonRequest request = new TriathlonRequest
            {
                Type = TriathlonRequest.Types.Type.GetNoted,
                Referee = theReferee
            };
            return request;
        }

        public static TriathlonRequest createNotNotedRequest(model.Referee referee)
        {
            proto.Referee theReferee = new proto.Referee
            {
                Id = referee.Id,
                Username = referee.Username,
                Passwrod = referee.Password,
                FirstName = referee.First_Name,
                LastName = referee.Last_Name,
                Activity = referee.Activity
            };
            TriathlonRequest request = new TriathlonRequest
            {
                Type = TriathlonRequest.Types.Type.GetNotNoted,
                Referee = theReferee
            };
            return request;
        }

        public static TriathlonRequest createGetPartRequest(int id)
        {
            TriathlonRequest request = new TriathlonRequest
            {
                Type = TriathlonRequest.Types.Type.GetPart,
                Id = id
            };
            return request;
        }
        
        
        // RESPONSE-URI
        public static TriathlonResponse createOkResponse()
        {
            TriathlonResponse response = new TriathlonResponse
            {
                Type = TriathlonResponse.Types.Type.Ok
            };
            return response;
        }
        
        public static TriathlonResponse createOkResponse(model.Referee referee)
        {
            proto.Referee theReferee = new proto.Referee
            {
                Id = referee.Id,
                FirstName = referee.First_Name,
                LastName = referee.Last_Name,
                Username = referee.Username,
                Passwrod = referee.Password,
                Activity = referee.Activity
            };
            TriathlonResponse response = new TriathlonResponse
            {
                Type = TriathlonResponse.Types.Type.Ok,
                Referee = theReferee
            };
            return response;
        }

        public static TriathlonResponse createErrorResponse(String text)
        {
            TriathlonResponse response = new TriathlonResponse
            {
                Type = TriathlonResponse.Types.Type.Error,
                Error = text
            };
            return response;
        }

        // public static TriathlonResponse createAddScoreResponse(model.Result result)
        // {
        //     proto.Result theResult = new proto.Result
        //     {
        //         Activity = result.Activity,
        //         Id = result.Id,
        //         ParticipantId = result.Participant.Id,
        //         Points = result.Points,
        //         RefereeId = result.Referee.Id
        //     };
        //     TriathlonResponse response = new TriathlonResponse
        //     {
        //         Type = TriathlonResponse.Types.Type.NewScore,
        //         Result = theResult
        //     };
        //     return response;
        // }
        
        public static TriathlonResponse createAddScoreResponse(model.Participant participant)
        {
            proto.Participant theParticipant = new proto.Participant()
            {
                Id =participant.Id,
                FirstName = participant.First_Name,
                LastName = participant.Last_Name,
                Points = participant.Points
            };
            TriathlonResponse response = new TriathlonResponse
            {
                Type = TriathlonResponse.Types.Type.NewScore,
                Participant = theParticipant
            };
            return response;
        }

        public static TriathlonResponse createGetTopResponse(List<model.Participant> participants)
        {
            TriathlonResponse response = new TriathlonResponse
            {
                Type = TriathlonResponse.Types.Type.GetTop
            };
            foreach (model.Participant part in participants)
            {
                proto.Participant participant = new proto.Participant
                {
                    Id = part.Id,
                    FirstName = part.First_Name,
                    LastName = part.Last_Name,
                    Points = part.Points
                };
                response.Participants.Add(participant);
            }

            return response;
        }

        public static TriathlonResponse createGetNotedPartResponse(List<model.Participant> participants)
        {
            TriathlonResponse response = new TriathlonResponse
            {
                Type = TriathlonResponse.Types.Type.GetNoted
            };
            foreach (model.Participant part in participants)
            {
                proto.Participant participant = new proto.Participant
                {
                    Id = part.Id,
                    FirstName = part.First_Name,
                    LastName = part.Last_Name,
                    Points = part.Points
                };
                response.Participants.Add(participant);
            }

            return response;
        }

        public static TriathlonResponse createGetNotNotedPartResponse(List<model.Participant> participants)
        {
            TriathlonResponse response = new TriathlonResponse
            {
                Type = TriathlonResponse.Types.Type.GetNotNoted
            };
            foreach (model.Participant part in participants)
            {
                proto.Participant participant = new proto.Participant
                {
                    Id = part.Id,
                    FirstName = part.First_Name,
                    LastName = part.Last_Name,
                    Points = part.Points
                };
                response.Participants.Add(participant);
            }
            return response;
        }

        public static TriathlonResponse createGetPart(model.Participant participant)
        {
            proto.Participant theParticipant = new proto.Participant
            {
                Id = participant.Id,
                FirstName = participant.First_Name,
                LastName = participant.Last_Name,
                Points = participant.Points
            };
            TriathlonResponse response = new TriathlonResponse
            {
                Type = TriathlonResponse.Types.Type.Part,
                Participant = theParticipant
            };
            return response;
        }
        
        
        // GET
        public static model.Referee getReferee(TriathlonRequest request)
        {
            model.Referee referee = new model.Referee(request.Referee.Id, request.Referee.Username,
                request.Referee.Passwrod, request.Referee.FirstName, request.Referee.LastName,
                request.Referee.Activity);
            return referee;
        }

        public static model.Result getResult(TriathlonRequest request)
        {
            model.Referee referee = new model.Referee(
                request.Result.RefereeId, "a", "a", "a", "a", "a");
            model.Participant participant = new model.Participant(
                request.Result.ParticipantId, "a", "a", 0);


            model.Result result = new model.Result(
                request.Result.Id,
                participant,
                referee,
                request.Result.Points,
                request.Result.Activity);
            return result;
        }

        public static int getId(TriathlonRequest request)
        {
            int id = request.Id;
            return id;
        }
    }
}