package com.KnorexAssignment;

import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VASTRepository {

    public void save(VASTTag vastTag) throws SQLException {
        String sql = "INSERT INTO vast_tags (id, version, title, description, impression_id, impression_url, creatives) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vastTag.getId());
            stmt.setString(2, vastTag.getVersion());
            stmt.setString(3, vastTag.getTitle());
            stmt.setString(4, vastTag.getDescription());
            stmt.setString(5, vastTag.getImpressionId());
            stmt.setString(6, vastTag.getImpressionUrl());
            stmt.setString(7, vastTag.toJson().getJSONArray("creatives").toString());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Failed to save VAST tag to the database", e);
        }
    }

    public VASTTag findById(String id) throws SQLException {
        String sql = "SELECT * FROM vast_tags WHERE id = ?";
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                VASTTag vastTag = new VASTTag();
                vastTag.setId(rs.getString("id"));
                vastTag.setVersion(rs.getString("version"));
                vastTag.setTitle(((ResultSet) rs).getString("title"));
                vastTag.setDescription(rs.getString("description"));
                vastTag.setImpressionId(rs.getString("impression_id"));
                vastTag.setImpressionUrl(rs.getString("impression_url"));

                JSONObject creativesJson = new JSONObject(rs.getString("creatives"));
                // Populate vastTag with creatives from JSON (omitted for brevity)

                return vastTag;
            }

            return null;
        } catch (SQLException e) {
            throw new SQLException("Failed to find VAST tag by ID", e);
        }
    }
}
